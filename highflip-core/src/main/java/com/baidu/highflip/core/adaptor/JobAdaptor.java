package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.engine.HighFlipRuntime;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface JobAdaptor {

    List<String> getFeatures();

    //Job Basic
    Job createJob(Job job);

    Job updateJob(Job job);

    boolean hasJob(Job job);

    Status getJobStatus(Job job);

    void deleteJob(Job job);

    Job controlJob(Job job, Action action);

    // Job Sync
    int getJobCount();

    Job getJobByIndex(int index, Job job);

    Optional<Job> moreJob(Job job, HighFlipRuntime runtime);

    // Tasks
    int getTaskCount(Job job);

    List<Task> getTaskList(Job job, List<Task> task);

    // Log
    int getJobLogCount(Job job);

    Iterator<String> getJobLog(Job job, int offset, int limit);
}
