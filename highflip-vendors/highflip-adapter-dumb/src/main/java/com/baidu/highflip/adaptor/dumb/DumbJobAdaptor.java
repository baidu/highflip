package com.baidu.highflip.adaptor.dumb;

import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Status;
import com.baidu.highflip.core.entity.runtime.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class DumbJobAdaptor implements JobAdaptor {

    @Override
    public Job createJob(Job job) {
        return null;
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
        return null;
    }

    @Override
    public void deleteJob(Job job) {

    }

    @Override
    public Job controlJob(Job job, Action action) {
        return null;
    }

    @Override
    public int getJobCount(Job job) {
        return 0;
    }

    @Override
    public Job getJobByIndex(int index, Job job) {
        return null;
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