package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Status;
import com.baidu.highflip.core.entity.runtime.Task;

import java.util.Iterator;
import java.util.List;

public interface JobAdaptor {

    //JOB
    Job createJob(Job job);

    Job updateJob(Job job);

    boolean hasJob(Job job);

    Status getJobStatus(Job job);

    void deleteJob(Job job);

    Job controlJob(Job job, Action action);

    int getJobCount(Job job);

    Job getJobByIndex(int index, Job job);

    int getTaskCount(Job job);

    List<Task> getTaskList(Job job, List<Task> task);

    int getJobLogCount(Job job);

    Iterator<String> getJobLog(Job job, int offset, int limit);
}
