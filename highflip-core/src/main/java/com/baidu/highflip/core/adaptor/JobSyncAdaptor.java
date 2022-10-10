package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Job;

public interface JobSyncAdaptor {

    int getJobCount();

    Job getJobByIndex(int index, Job job);

    Job moreJob(Job job);
}
