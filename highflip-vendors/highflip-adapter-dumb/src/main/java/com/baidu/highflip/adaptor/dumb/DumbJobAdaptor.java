package com.baidu.highflip.adaptor.dumb;

import com.baidu.highflip.core.engine.adaptor.JobAdaptor;
import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Slf4j
@Component
public class DumbJobAdaptor implements JobAdaptor {


    @Override
    public Job createJob(Job job) {
        return null;
    }

    @Override
    public Job getJob(Job job) {
        return null;
    }

    @Override
    public boolean hasJob(Job job) {
        return false;
    }

    @Override
    public void deleteJob(Job job) {

    }

    @Override
    public void controlJob(Job job, Action action) {

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
    public Task getTaskByIndex(int index, Task task) {
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
