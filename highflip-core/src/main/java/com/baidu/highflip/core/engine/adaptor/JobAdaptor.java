package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Job;

public interface JobAdaptor {

    //JOB
    String createJob(Graph graph);

    Job getJob(Job job);

    void deleteJob(Job job);

    void controlJob(Job job, String action);
}
