package com.baidu.highflip.adaptor.dumb;

import com.baidu.highflip.core.entity.runtime.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Slf4j
@Component
public class JobAdaptor  {

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


    public Iterator<String> getJobLog(Job job) {
        return null;
    }
}
