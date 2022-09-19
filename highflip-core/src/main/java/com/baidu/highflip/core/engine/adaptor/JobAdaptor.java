package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Job;

import java.util.Iterator;

public interface JobAdaptor {

    //JOB
    Job createJob(Job job);

    Job getJob(Job job);

    void deleteJob(Job job);

    void controlJob(Job job, String action);

    Iterator<String> getJobLog(Job job);
}
