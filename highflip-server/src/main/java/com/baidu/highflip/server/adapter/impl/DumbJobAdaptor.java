package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.engine.HighFlipRuntime;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class DumbJobAdaptor implements JobAdaptor {
    @Override
    public List<String> getFeatures() {
        return null;
    }

    @Override
    public Job createJob(Job job) {
        log.info("created a job {}", job);
        return job;
    }

    @Override
    public Job updateJob(Job job) {
        return null;
    }

    @Override
    public boolean hasJob(Job job) {
        return false;
    }

    @Override
    public Status getJobStatus(Job job) {
        log.info("get job status {}", job);
        return Status.SUCCEEDED;
    }

    @Override
    public void deleteJob(Job job) {
        log.info("deleted a job {}", job);
    }

    @Override
    public Job controlJob(Job job, Action action) {
        log.info("control a job {} action {}", job, action);
        return job;
    }

    @Override
    public int getJobCount() {
        return 0;
    }

    @Override
    public Job getJobByIndex(int index, Job job) {
        return null;
    }

    @Override
    public Optional<Job> moreJob(Job job, HighFlipRuntime runtime) {
        return Optional.empty();
    }

    @Override
    public int getTaskCount(Job job) {
        return 0;
    }

    @Override
    public List<Task> getTaskList(Job job, List<Task> task) {
        return null;
    }

    @Override
    public int getJobLogCount(Job job) {
        return 0;
    }

    @Override
    public Iterator<String> getJobLog(Job job, int offset, int limit) {
        return null;
    }
}
