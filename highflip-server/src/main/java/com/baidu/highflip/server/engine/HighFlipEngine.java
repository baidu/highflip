package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.engine.adaptor.JobAdaptor;
import com.baidu.highflip.core.engine.adaptor.TaskAdaptor;
import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.*;
import com.baidu.highflip.server.respository.JobRepository;
import com.baidu.highflip.server.respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public abstract class HighFlipEngine {

    @Autowired
    JobRepository jobs;

    @Autowired
    JobAdaptor jobAd;


    @Autowired
    TaskRepository tasks;

    @Autowired
    TaskAdaptor taskAd;

    //PLATFORM
    public abstract String getPlatform();

    public abstract List<String> listPlatform();

    //JOB
    public String createJob(Graph graph){
        Job job = new Job();
        jobAd.createJob(graph);
        jobs.save(job);
        return job.getJobid();
    }

    public Job getJob(String jobid){
        Job job = jobs.findById(jobid).get();
        job = jobAd.getJob(job);
        jobs.save(job);
        return job;
    }

    public abstract List<String> listJob();

    public void deleteJob(String jobid){
        Job job = jobs.findById(jobid).get();
        jobAd.deleteJob(job);
        jobs.delete(job);
    }

    public abstract void controlJob(String jobid, String action);

    //TASK
    public List<Task> listTask(String jobid){
        return tasks.findAll();
    }

    public abstract Task getTask(String taskid);

    public abstract Iterator<String> getTaskLog(String taskid);

    public abstract void invokeTask(String taskid);

    //DATA
    public abstract List<String> listData();

    public abstract Data getData(String dataid);

    public abstract void deleteData(String dataid);

    public abstract Iterator<List> fetchData(String dataid, long offset, long size);

    public abstract void provideData(String dataid);

    //FUNCTION
    public abstract List<String> listFunction();

    public abstract Function getFunction(String funcid);

    //PARTY
    public abstract List<String> listParty();

    public abstract Party getParty(String partyid);
}
