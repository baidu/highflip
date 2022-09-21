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


    public Job createJob(Job job) {
        return null;
    }

    public Job getJob(Job job) {
        return null;
    }

    public boolean hasJob(Job job) {
        return false;
    }

    public void deleteJob(Job job) {

    }

    public void controlJob(Job job, Action action) {

    }

    public int getJobCount() {
        return 0;
    }

    public Job getJobByIndex(int index, Job job) {
        return null;
    }

    public int getTaskCount(Job job) {
        return 0;
    }

    public Task getTaskByIndex(int index, Task task) {
        return null;
    }

    public int getJobLogCount(Job job) {
        return 0;
    }

    public Iterator<String> getJobLog(Job job, int offset, int limit) {
        return null;
    }

    public Iterator<String> getJobLog(Job job) {
        return null;
    }
}
