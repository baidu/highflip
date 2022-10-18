package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.adaptor.PlatformAdaptor;
import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Operator;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Platform;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import com.baidu.highflip.core.entity.runtime.version.CompatibleVersion;
import com.baidu.highflip.core.entity.runtime.version.PlatformVersion;
import com.baidu.highflip.core.utils.Foreach;
import com.baidu.highflip.server.engine.common.ConfigurationList;
import com.baidu.highflip.server.engine.component.HighFlipConfiguration;
import com.baidu.highflip.server.engine.component.HighFlipContext;
import com.baidu.highflip.server.respository.DataRepository;
import com.baidu.highflip.server.respository.JobRepository;
import com.baidu.highflip.server.respository.OperatorRepository;
import com.baidu.highflip.server.respository.PartnerRepository;
import com.baidu.highflip.server.respository.PlatformRepository;
import com.baidu.highflip.server.respository.TaskRepository;
import com.baidu.highflip.server.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class HighFlipEngine {

    @Autowired
    HighFlipContext context;

    @Autowired
    HighFlipConfiguration configuration;

    @Autowired
    PlatformRepository platformReps;

    @Autowired
    UserRepository userReps;

    @Autowired
    JobRepository jobReps;

    @Autowired
    TaskRepository taskReps;

    @Autowired
    DataRepository dataReps;

    @Autowired
    OperatorRepository operatorReps;

    @Autowired
    PartnerRepository partnerReps;


    ConcurrentMap<String, Job> activeJobs;

    /******************************************************************************
     * COMMON
     ******************************************************************************/

    public HighFlipContext getContext() {
        return context;
    }

    public HighFlipConfiguration getConfiguration() {
        return configuration;
    }

    @PostConstruct
    private void initialize() {
        initPlatform();
    }

    @PreDestroy
    private void destroy() {

    }

    /******************************************************************************
     * PLATFORM
     ******************************************************************************/
    @Transactional
    protected void initPlatform() {
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_PLATFORM_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_PLATFORM_IS_INITIALIZED_DEFAULT);

        if (isInitialized) {
            return;
        }

        log.info("Begin to initialize platform information.");

        platformReps.deleteLocal();
        log.info("Delete old platform information.");

        PlatformAdaptor adaptor = getContext().getPlatformAdaptor();
        if (adaptor == null) {
            return;
        }

        Platform platform = new Platform();
        platform.setCompany(adaptor.getCompany());
        platform.setProduct(adaptor.getProduct());
        platform.setVersion(adaptor.getVersion());
        platform.setIsLocal(Boolean.TRUE);

        List<CompatibleVersion> compatibles = StreamSupport
                .stream(Foreach.from(adaptor.getCompatibleList()).spliterator(), false)
                .collect(Collectors.toList());
        platform.setCompatibles(compatibles);

        platformReps.save(platform);

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_PLATFORM_IS_INITIALIZED,
                Boolean.TRUE);

        log.info("Finish initialization of platform information.");
    }

    public Platform getPlatform() {
        Platform platform = platformReps.findLocal();
        return platform;
    }

    public Platform matchPlatform(PlatformVersion target) {
        for (Platform platform : platformReps.findAll()) {
            for (CompatibleVersion comp : platform.getCompatibles()) {
                if (comp.isCompatible(target)) {
                    return platform;
                }
            }
        }
        return null;
    }

    /******************************************************************************
     * JOB
     ******************************************************************************/
    @Transactional
    public void initializeJobs() {
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_JOB_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_JOB_IS_INITIALIZED_DEFAULT);

        if (isInitialized){
            return;
        }

        log.info("Delete old job information.");
        jobReps.deleteAll();

        int jobCount = getContext().getJobAdaptor().getJobCount();
        for (int i = 0; i < jobCount; i++) {
            Job job = new Job();

            Job newJob = getContext().getJobAdaptor()
                    .getJobByIndex(i, job);

            jobReps.save(newJob);
        }

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_JOB_IS_INITIALIZED,
                Boolean.TRUE);
    }

    @Transactional
    @CachePut("jobs")
    public Job createJob(String name, String description, Graph graph) {
        Job job = new Job();
        job.setJobName(name);
        job.setDescription(description);
        job.setGraph(graph);

        getContext()
                .getJobAdaptor()
                .createJob(job);
        jobReps.save(job);

        int taskCount = getContext()
                .getJobAdaptor()
                .getTaskCount(job);

        ArrayList<Task> tasks = new ArrayList<>(taskCount);
        for (int i = 0; i < taskCount; i++) {
            Task task = new Task();
            taskReps.save(task);
            tasks.add(task);
        }

        List<Task> news = getContext()
                .getJobAdaptor()
                .getTaskList(job, tasks);

        taskReps.saveAll(news);
        return job;
    }


    // @Scheduled
    protected void updateJob() {

        JobAdaptor adaptor = getContext().getJobAdaptor();

        activeJobs.forEach((job_id, job) -> {
            Status status = adaptor.getJobStatus(job);
            if (status != job.getStatus()) {
                job.setStatus(status);
                jobReps.save(job);
            }
        });
    }

    @Cacheable("jobs")
    public Job getJob(String jobid) {
        Job job = jobReps.findById(jobid)
                .orElseThrow();

        return job;
    }

    public Iterator<String> listJobIds() {
        return jobReps.findAll()
                .stream()
                .map(job -> job.getJobId())
                .iterator();
    }

    @Transactional
    public void deleteJob(String jobid) {
        Job job = getJob(jobid);

        getContext().getJobAdaptor()
                .deleteJob(job);

        jobReps.delete(job);
    }

    public void controlJob(String jobid, Action action) {
        Job job = getJob(jobid);

        getContext().getJobAdaptor()
                .controlJob(job, action);
    }

    /******************************************************************************
     * TASK
     ******************************************************************************/

    @Transactional
    public void initializeTasks() {
        taskReps.deleteAll();

        int taskCount = getContext()
                .getTaskAdaptor()
                .getTaskCount();

        for (int i = 0; i < taskCount; i++) {
            Task task = new Task();

            Task newTask = getContext().getTaskAdaptor()
                    .getTaskByIndex(i, task);

            taskReps.save(newTask);
        }
    }

    // @Scheduled
    private void updateTask() {

    }

    public List<Task> listTask(String jobid) {

        return taskReps.findAllByJobid(jobid);
    }

    @Cacheable(value = "tasks")
    public Task getTask(String taskid) {

        return taskReps.findById(taskid)
                .orElseThrow();
    }

    public Iterator<String> getTaskLog(String taskid) {
        Task task = getTask(taskid);
        return getContext()
                .getTaskAdaptor()
                .getTaskLog(task, 0, 0);
    }

    public void invokeTask(String taskid) {
        throw new UnsupportedOperationException();
    }

    /******************************************************************************
     * DATA
     ******************************************************************************/
    protected void initializeData(){
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_DATA_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_DATA_IS_INITIALIZED_DEFAULT);

        if (isInitialized){
            return;
        }

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_DATA_IS_INITIALIZED,
                Boolean.TRUE);
    }

    public Iterator<String> listData() {
        return dataReps.findAll()
                .stream()
                .map(d -> d.getDataId())
                .iterator();
    }

    @Cacheable("data")
    public Data getData(String dataid) {
        return dataReps.findById(dataid)
                .orElseThrow();
    }

    @Transactional
    public void deleteData(String dataid) {
        Data data = getData(dataid);

        getContext().getDataAdaptor()
                .deleteData(data);

        dataReps.delete(data);
    }

    public Iterator<List<Object>> fetchData(String dataid, long offset, long size) {
        Data data = getData(dataid);

        return getContext().getDataAdaptor()
                .readData(data, DataAdaptor.PositionType.ROW, offset, size);
    }

    public void provideData(String dataid) {
        Data data = getData(dataid);

    }

    /******************************************************************************
     * OPERATOR
     ******************************************************************************/
    public Iterator<String> listOperator() {
        return operatorReps.findAll()
                .stream()
                .map(a -> a.getOperatorId())
                .iterator();
    }

    public Operator getOperator(String operatorId) {
        return operatorReps.findById(operatorId)
                .orElseThrow();
    }

    /******************************************************************************
     * PARTNER
     ******************************************************************************/
    public Partner getPartner(String partnerid) {
        return partnerReps.findById(partnerid)
                .orElseThrow();
    }

    public Iterator<String> listPartner(int offset, int limit) {
        return partnerReps.findAll()
                .stream()
                .map(p -> p.getPartnerId())
                .iterator();
    }
}
