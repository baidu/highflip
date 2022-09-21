package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Task;

import java.util.Iterator;

public interface JobAdaptor {

    //JOB
    Job createJob(Job job);

    Job getJob(Job job);

    boolean hasJob(Job job);

    void deleteJob(Job job);

    void controlJob(Job job, Action action);

    int getJobCount();

    Job getJobByIndex(int index, Job job);

    int getTaskCount(Job job);

    Task getTaskByIndex(int index, Task task);

    int getJobLogCount(Job job);

    Iterator<String> getJobLog(Job job, int offset, int limit);
}
