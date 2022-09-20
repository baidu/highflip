package com.baidu.highflip.core.engine.scheduler;

import com.baidu.highflip.core.entity.runtime.Job;

public abstract class AbstractScheduler {

    public abstract void dispatch(Job job);

    public abstract void status();
}
