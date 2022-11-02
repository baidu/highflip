package com.baidu.highflip.adaptor.demo;

import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class DemoJobAdaptor implements JobAdaptor {

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
    public int getJobCount() {
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
