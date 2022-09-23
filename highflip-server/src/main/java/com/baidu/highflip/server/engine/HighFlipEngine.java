package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Algorithm;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.server.respository.AlgorithmRepository;
import com.baidu.highflip.server.respository.DataRepository;
import com.baidu.highflip.server.respository.JobRepository;
import com.baidu.highflip.server.respository.PartnerRepository;
import com.baidu.highflip.server.respository.TaskRepository;
import com.baidu.highflip.server.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class HighFlipEngine {

    @Autowired
    HighFlipContext context;

    @Autowired
    UserRepository userReps;

    @Autowired
    JobRepository jobReps;

    @Autowired
    TaskRepository taskReps;

    @Autowired
    DataRepository dataReps;

    @Autowired
    AlgorithmRepository algorithmReps;

    @Autowired
    PartnerRepository partnerReps;

    ///////////////////////////////////////////////////////////////////////////////
    // COMMON
    ///////////////////////////////////////////////////////////////////////////////

    public HighFlipContext getContext(){
        return context;
    }

    @PostConstruct
    private void initialize(){

    }
    ///////////////////////////////////////////////////////////////////////////////
    // PLATFORM
    ///////////////////////////////////////////////////////////////////////////////
    public String getPlatform() {
        throw new UnsupportedOperationException();
    }

    public List<String> listPlatform() {
        throw new UnsupportedOperationException();
    }

    ///////////////////////////////////////////////////////////////////////////////
    // JOB
    ///////////////////////////////////////////////////////////////////////////////
    @Transactional
    public String createJob(String name, Graph graph) {
        Job job = new Job();
        job.setJobName(name);
        job.setGraph(graph);

        context.getJobAdaptor().createJob(job);
        jobReps.save(job);
        return job.getJobId();
    }


    @Scheduled
    protected void updateJob(){

    }

    public Job getJob(String jobid) {
        Job job = jobReps.findById(jobid).orElseThrow();
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
        Job job = jobReps.findById(jobid).orElseThrow();

        getContext().getJobAdaptor()
                .deleteJob(job);

        jobReps.delete(job);
    }

    public void controlJob(String jobid, Action action) {
        Job job = jobReps.findById(jobid).orElseThrow();

        getContext().getJobAdaptor()
                .controlJob(job, action);
    }

    ///////////////////////////////////////////////////////////////////////////////
    // TASK
    ///////////////////////////////////////////////////////////////////////////////
    public List<Task> listTask(String jobid) {

        return taskReps.findAllByJobid(jobid);
    }

    public Task getTask(String taskid) {

        return taskReps.findById(taskid).orElseThrow();
    }

    public Iterator<String> getTaskLog(String taskid) {
        Task task = getTask(taskid);
        return getContext().getTaskAdaptor().getTaskLog(task, 0, 0);
    }

    public void invokeTask(String taskid) {
        throw new UnsupportedOperationException();
    }

    ///////////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////////
    public Iterator<String> listData() {
        return dataReps.findAll()
                .stream()
                .map(d -> d.getDataId())
                .iterator();
    }

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

    ///////////////////////////////////////////////////////////////////////////////
    // ALGORITHM
    ///////////////////////////////////////////////////////////////////////////////
    public Iterator<String> listAlgorithm() {
        return algorithmReps.findAll()
                .stream()
                .map(a -> a.getId())
                .iterator();
    }

    public Algorithm getAlgorithm(String algid) {
        return algorithmReps.findById(algid).orElseThrow();
    }

    ///////////////////////////////////////////////////////////////////////////////
    // PARTNER
    ///////////////////////////////////////////////////////////////////////////////
    public Iterator<String> listPartner() {
        return partnerReps.findAll()
                .stream()
                .map(p -> p.getId())
                .iterator();
    }

    public Partner getPartner(String partnerid) {
        return partnerReps.findById(partnerid)
                .orElseThrow();
    }
}
