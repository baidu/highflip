package com.baidu.highflip.server.engine.adapter.dumb;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Slf4j
@Component
public class JobAdaptor implements com.baidu.highflip.core.engine.adaptor.JobAdaptor {
    public Job createJob(Job job) {
        return null;
    }

    public Job getJob(Job job) {
        return null;
    }

    public void deleteJob(Job job) {

    }

    public void controlJob(Job job, String action) {

    }

    @Override
    public Iterator<String> getJobLog(Job job) {
        return null;
    }
}
