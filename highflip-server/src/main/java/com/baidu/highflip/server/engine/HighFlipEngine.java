package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.engine.adaptor.JobAdaptor;
import com.baidu.highflip.core.engine.adaptor.TaskAdaptor;
import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.*;
import com.baidu.highflip.server.respository.JobRepository;
import com.baidu.highflip.server.respository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class HighFlipEngine {

    @Autowired
    JobRepository jobs;

    @Autowired
    JobAdaptor jobAd;


    @Autowired
    TaskRepository tasks;

    @Autowired
    TaskAdaptor taskAd;

    //PLATFORM
    public String getPlatform(){
        throw new UnsupportedOperationException();
    }

    public List<String> listPlatform(){
        throw new UnsupportedOperationException();
    }

    // JOB
    @Transactional
    public String createJob(String name, Graph graph){
        Job job = new Job();
        job.setJobName(name);
        // job.setGraph(graph);

        jobAd.createJob(job);
        jobs.save(job);
        return job.getJobid();
    }

    @Transactional
    public Job getJob(String jobid){
        Job job = jobs.findById(jobid).get();
        job = jobAd.getJob(job);
        jobs.save(job);
        return job;
    }

    public Iterator<String> listJob(){
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void deleteJob(String jobid){
        Job job = jobs.findById(jobid).get();
        jobAd.deleteJob(job);
        jobs.delete(job);
    }

    public void controlJob(String jobid, String action){
        throw new UnsupportedOperationException();
    }

    // TASK
    public List<Task> listTask(String jobid){

        return tasks.findAllByJobid(jobid);
    }

    public Task getTask(String taskid){
        throw new UnsupportedOperationException();
    }

    public Iterator<String> getTaskLog(String taskid){
        throw new UnsupportedOperationException();
    }

    public void invokeTask(String taskid){
        throw new UnsupportedOperationException();
    }

    // DATA
    public List<String> listData(){
        throw new UnsupportedOperationException();
    }

    public Data getData(String dataid){
        throw new UnsupportedOperationException();
    }

    public void deleteData(String dataid){
        throw new UnsupportedOperationException();
    }

    public Iterator<List> fetchData(String dataid, long offset, long size){
        throw new UnsupportedOperationException();
    }

    public void provideData(String dataid){
        throw new UnsupportedOperationException();
    }

    // FUNCTION
    public List<String> listFunction(){
        throw new UnsupportedOperationException();
    }

    public Function getFunction(String funcid){
        throw new UnsupportedOperationException();
    }

    // PARTY
    public List<String> listParty(){
        throw new UnsupportedOperationException();
    }

    public Party getParty(String partyid){
        throw new UnsupportedOperationException();
    }
}
