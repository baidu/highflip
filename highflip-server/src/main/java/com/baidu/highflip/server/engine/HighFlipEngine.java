package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.adaptor.PlatformAdaptor;
import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Config;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Operator;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Platform;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.User;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Column;
import com.baidu.highflip.core.entity.runtime.basic.DataMode;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import com.baidu.highflip.core.entity.runtime.version.CompatibleVersion;
import com.baidu.highflip.core.entity.runtime.version.PlatformVersion;
import com.baidu.highflip.server.engine.common.ConfigurationList;
import com.baidu.highflip.server.engine.component.HighFlipConfiguration;
import com.baidu.highflip.server.engine.component.HighFlipContext;
import com.baidu.highflip.server.engine.component.HighFlipRuntime;
import com.baidu.highflip.server.engine.dataio.PushContext;
import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HighFlipEngine {

    @Autowired
    HighFlipContext context;

    @Autowired
    HighFlipConfiguration configuration;

    @Autowired
    HighFlipRuntime runtime;

    @Autowired
    PlatformTransactionManager transactionManager;

    ConcurrentMap<String, Job> activeJobs;

    @Autowired
    AsyncTaskExecutor executor;

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
    public void initialize() {
        new TransactionTemplate(transactionManager).execute(
                new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        initPlatform();

                        initializePartners();
                    }
                });
    }

    @PreDestroy
    public void destroy() {

    }

    /******************************************************************************
     * CONFIG
     ******************************************************************************/
     public Iterable<String> listConfig(){
         return getConfiguration()
                 .listKeys();
     }

    public void setConfig(String key, String value){
        getConfiguration()
                .setString(key, value);
    }

    public Config getConfig(String key){
         return getConfiguration()
                .getEntry(key);
    }

    public void deleteConfig(String key){
        getConfiguration()
                .delete(key);
    }

    /******************************************************************************
     * PLATFORM
     ******************************************************************************/
    @Transactional
    public void initPlatform() {
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_PLATFORM_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_PLATFORM_IS_INITIALIZED_DEFAULT);

        if (isInitialized) {
            return;
        }

        loadPlatform();

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_PLATFORM_IS_INITIALIZED,
                Boolean.TRUE);

        log.info("Finish initialization of platform information.");
    }

    @Transactional
    public void loadPlatform(){
        log.info("Begin to initialize platform information.");

        getContext().getPlatformRepository()
                .deleteLocal();
        log.info("Delete old platform information.");

        PlatformAdaptor adaptor = getContext().getPlatformAdaptor();
        if (adaptor == null) {
            log.error("Miss platform adaptor in system. Skipped platform initialization.");
            return;
        }

        Platform platform = new Platform();
        platform.setCompany(adaptor.getCompany());
        platform.setProduct(adaptor.getProduct());
        platform.setVersion(adaptor.getVersion());
        platform.setIsLocal(Boolean.TRUE);

        Iterator<CompatibleVersion> iter = adaptor.getCompatibleList();
        if (iter != null) {
            List<CompatibleVersion> compatibles = Streams
                    .stream(iter)
                    .collect(Collectors.toList());
            platform.setCompatibles(compatibles);
        }
        getContext().getPlatformRepository().save(platform);
    }

    public Platform getPlatform() {
        Platform platform = getContext().getPlatformRepository().findLocal();
        return platform;
    }

    /**
     *
     * @param target
     * @return
     */
    public Platform matchPlatform(PlatformVersion target) {
        for (Platform platform : getContext().getPlatformRepository().findAll()) {
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

        if (isInitialized) {
            return;
        }

        loadJobs();

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_JOB_IS_INITIALIZED,
                Boolean.TRUE);
    }

    /**
     *
     */
    @Transactional
    public void loadJobs(){
        log.info("Delete old job information.");
        getContext().getJobRepository()
                .deleteAll();

        int jobCount = getContext()
                .getJobAdaptor()
                .getJobCount();

        for (int i = 0; i < jobCount; i++) {
            Job job = new Job();

            Job newJob = getContext().getJobAdaptor()
                    .getJobByIndex(i, job);

            getContext().getJobRepository()
                    .save(newJob);

            log.info("Load a job information.");
        }
    }

    @Transactional
    public Job synchronizeJob() {
        Job job = new Job();
        Optional<Job> optJob = getContext().getJobAdaptor().moreJob(job, runtime);

        if (optJob == null || optJob.isEmpty()) {
            return null;
        }

        if (runtime.hasJobByBindId(job.getBingingId())) {
            log.error("Detected and skipped duplicated Binding Id {} in jobs synchronization.",
                    job.getBingingId());
        }

        Job savedJob = getContext().getJobRepository()
                .save(job);
        log.info("Found new job, jobId = {}", savedJob.getJobId());
        return savedJob;
    }

    @Scheduled(fixedDelayString = "10s")
    public void synchronizeJobs() {
        try {
            Job job = null;
            do {
                job = synchronizeJob();
            } while (job != null);
        } catch (Exception e) {

        }
    }

    /**
     *
     * @param name
     * @param description
     * @param graph
     * @return
     */
    @Transactional
    @CachePut("jobs")
    public Job createJob(String name, String description, Graph graph) {
        Job job = new Job();
        job.setJobName(name);
        job.setDescription(description);
        job.setGraph(graph);

        job = getContext().getJobAdaptor()
                .createJob(job);
        job = getContext().getJobRepository()
                .save(job);

        int taskCount = getContext()
                .getJobAdaptor()
                .getTaskCount(job);

        if (taskCount > 0) {
            ArrayList<Task> tasks = new ArrayList<>(taskCount);
            for (int i = 0; i < taskCount; i++) {
                Task task = new Task();
                getContext().getTaskRepository()
                        .save(task);
                tasks.add(task);
            }

            List<Task> news = getContext()
                    .getJobAdaptor()
                    .getTaskList(job, tasks);

            getContext().getTaskRepository()
                    .saveAll(news);
        }
        return job;
    }

    // @Scheduled
    protected void updateJob() {

        JobAdaptor adaptor = getContext().getJobAdaptor();

        activeJobs.forEach((job_id, job) -> {
            Status status = adaptor.getJobStatus(job);
            if (status != job.getStatus()) {
                job.setStatus(status);
                getContext().getJobRepository().save(job);
            }
        });
    }

    @Cacheable("jobs")
    public Job getJob(String jobid) {
        Job job = getContext().getJobRepository().findById(jobid)
                .orElseThrow();

        return job;
    }

    public Iterator<String> listJobIds() {
        return getContext().getJobRepository().findAll()
                .stream()
                .map(job -> job.getJobId())
                .iterator();
    }

    @Transactional
    public void deleteJob(String jobId) {
        Job job = getJob(jobId);

        getContext().getJobAdaptor()
                .deleteJob(job);

        getContext().getJobRepository().delete(job);
    }

    public void controlJob(String jobId, Action action, Map<String, String> config) {
        Job job = getJob(jobId);

        getContext().getJobAdaptor()
                .controlJob(job, action);
    }

    public Iterable<String> getJobLog(String jobId) {
        Job job = getJob(jobId);

        int count = getContext().getJobAdaptor()
                .getJobLogCount(job);

        new Iterator<String>() {

            int current = 0;

            @Override
            public boolean hasNext() {
                return current < count;
            }

            @Override
            public String next() {
                getContext().getJobAdaptor()
                        .getJobLog(job, 0, 0);
                return null;
            }
        };
        return null;
    }

    /******************************************************************************
     * TASK
     ******************************************************************************/
    @Transactional
    public void initializeTasks() {
        getContext().getTaskRepository()
                .deleteAll();

        int taskCount = getContext()
                .getTaskAdaptor()
                .getTaskCount();

        for (int i = 0; i < taskCount; i++) {
            Task task = new Task();

            Task newTask = getContext().getTaskAdaptor()
                    .getTaskByIndex(i, task);

            getContext().getTaskRepository()
                    .save(newTask);
        }
    }

    // @Scheduled
    private void updateTask() {

    }

    public Iterable<Task> listTask(String jobid) {

        return getContext().getTaskRepository()
                .findAllByJobid(jobid);
    }

    @Cacheable(value = "tasks")
    public Task getTask(String taskId) {

        return getContext()
                .getTaskRepository()
                .findById(taskId)
                .orElseThrow();
    }

    public void controlTask(String taskId, Action action, Map<String, String> config) {
        Task task = getTask(taskId);

        getContext().getTaskAdaptor()
                .controlTask(task, action, config);
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
    @Transactional
    protected void initializeData() {
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_DATA_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_DATA_IS_INITIALIZED_DEFAULT);

        if (isInitialized) {
            return;
        }

        int count = getContext().getDataAdaptor()
                .getDataCount();

        for (int i = 0; i < count; i++) {
            Data data = new Data();
            Data retData = getContext().getDataAdaptor()
                    .getDataByIndex(i, data);

            Data saveData = getContext().getDataRepository()
                    .save(retData);

            log.info("Initialize a data {}", saveData.getDataId());
        }

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_DATA_IS_INITIALIZED,
                Boolean.TRUE);
    }

    /**
     * @return
     */
    public Iterable<String> listData() {
        return () -> getContext()
                .getDataRepository()
                .findAll()
                .stream()
                .map(d -> d.getDataId())
                .iterator();
    }

    /**
     * @param dataid
     * @return
     */
    @Cacheable("data")
    public Data getData(String dataid) {
        return getContext()
                .getDataRepository()
                .findById(dataid)
                .orElseThrow();
    }

    @Transactional
    public void deleteData(String dataid) {
        Data data = getData(dataid);

        getContext().getDataAdaptor()
                .deleteData(data);

        getContext()
                .getDataRepository()
                .delete(data);
    }

    public PushContext pushData(
            String name,
            String description,
            DataMode format,
            List<Column> columns) {

        Data data = new Data();
        data.setName(name);
        data.setDescription(description);
        data.setColumns(columns);
        data.setFormat(format);
        getContext().getDataRepository().save(data);

        switch (format) {
            case DENSE:
                return PushContext.createDense(
                        getContext().getDataAdaptor(), data);
            case SPARSE:
                return PushContext.createSparse(
                        getContext().getDataAdaptor(), data);
            default:
            case RAW:
                return PushContext.createRaw(
                        getContext().getDataAdaptor(), data);
        }
    }

    public InputStream pullDataRaw(String dataid, long offset, long size) {
        Data data = getData(dataid);

        return getContext().getDataAdaptor()
                .readDataRaw(data);
    }


    public Iterator<List<Object>> pullDataDense(String dataid, long offset, long size) {
        Data data = getData(dataid);

        return getContext().getDataAdaptor()
                .readDataDense(data);
    }

    public Iterator<List<KeyPair>> pullDataSparse(String dataid, long offset, long size) {
        Data data = getData(dataid);

        return getContext().getDataAdaptor()
                .readDataSparse(data);
    }

    /******************************************************************************
     * OPERATOR
     ******************************************************************************/
    @Transactional
    protected void initializeOperator() {
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_OPERATOR_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_OPERATOR_IS_INITIALIZED_DEFAULT);

        if (isInitialized) {
            return;
        }

        int count = getContext()
                .getOperatorAdaptor()
                .getOperatorCount();

        for (int i = 0; i < count; i++) {
            Operator oper = new Operator();

            oper = getContext()
                    .getOperatorAdaptor()
                    .getOperatorByIndex(i, oper);

            oper = getContext()
                    .getOperatorRepository()
                    .save(oper);

            log.info("Initialize an operator {}", oper.getOperatorId());
        }

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_OPERATOR_IS_INITIALIZED,
                Boolean.TRUE);
    }

    public Iterator<String> listOperator() {
        return getContext()
                .getOperatorRepository()
                .findAll()
                .stream()
                .map(a -> a.getOperatorId())
                .iterator();
    }

    public Operator getOperator(String operatorId) {
        return getContext()
                .getOperatorRepository()
                .findById(operatorId)
                .orElseThrow();
    }

    /******************************************************************************
     * PARTNER
     ******************************************************************************/
    @Transactional
    protected void initializePartners() {
        Boolean isInitialized = getConfiguration().getBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_PARTNER_IS_INITIALIZED,
                ConfigurationList.CONFIG_HIGHFLIP_PARTNER_IS_INITIALIZED_DEFAULT);

        if (isInitialized) {
            return;
        }

        loadPartners();

        getConfiguration().setBoolean(
                ConfigurationList.CONFIG_HIGHFLIP_PARTNER_IS_INITIALIZED,
                Boolean.TRUE);
    }

    public void loadPartners(){
        int count = getContext()
                .getPartnerAdaptor()
                .getPartnerCount();

        for (int i = 0; i < count; i++) {
            Partner partner = new Partner();

            partner = getContext()
                    .getPartnerAdaptor()
                    .getPartnerByIndex(i, partner);

            partner = getContext()
                    .getPartnerRepository()
                    .save(partner);

            log.info("Load a partner {}", partner.getPartnerId());
        }
    }

    /**
     *
     * @param name
     * @param description
     * @return
     */
    @Transactional
    public String createPartner(String name, String description) {
        Partner partner = new Partner();
        partner.setName(name);
        partner.setDescription(description);

        Partner retPartner = getContext().getPartnerAdaptor()
                .createPartner(partner);

        return getContext().getPartnerRepository()
                .save(retPartner)
                .getPartnerId();
    }

    @Transactional
    public void deletePartner(String partnerId){
        Partner partner = getPartner(partnerId);

        getContext().getPartnerAdaptor()
                .deletePartner(partner);

        getContext()
                .getPartnerRepository()
                .delete(partner);
    }

    public Partner getPartner(String partnerId) {
        return getContext()
                .getPartnerRepository()
                .findById(partnerId)
                .orElseThrow();
    }

    public Iterable<String> listPartner(int offset, int limit) {
        return () -> getContext()
                .getPartnerRepository()
                .findAll()
                .stream()
                .map(Partner::getPartnerId)
                .iterator();
    }

    /******************************************************************************
     * USER
     ******************************************************************************/
    @Transactional
    public String createUser(User user){
        getContext().getUserAdaptor()
                .createUser(user);

        return getContext()
                .getUserRepository()
                .save(user)
                .getUserId();
    }

    @Transactional
    public void deleteUser(String userId){
        User user = getUser(userId);

        getContext().getUserAdaptor()
                .deleteUser(user);

        getContext()
            .getUserRepository()
            .delete(user);
    }

    @Cacheable("user")
    public User getUser(String userId){
        return getContext()
                .getUserRepository()
                .findById(userId)
                .orElseThrow();
    }

    public boolean validateUser(String user, String pass){
        log.info("validate user={}", user);
        return true;
    }

    public Iterable<String> listUser(){
        return () -> getContext()
                .getUserRepository()
                .findAll()
                .stream()
                .map(User::getUserId)
                .iterator();
    }
}
