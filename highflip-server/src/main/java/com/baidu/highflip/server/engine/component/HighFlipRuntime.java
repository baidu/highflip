package com.baidu.highflip.server.engine.component;

import com.baidu.highflip.core.engine.Configuration;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Operator;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HighFlipRuntime implements com.baidu.highflip.core.engine.HighFlipRuntime {

    @Autowired
    HighFlipContext context;

    HighFlipContext getContext() {
        return context;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public Job getJob(String jobId) {
        return getContext()
                .getJobRepository()
                .findById(jobId)
                .orElse(null);
    }

    @Override
    public Boolean hasJobByBindId(String bindId) {
        return null;
    }

    @Override
    public Task getTask(String taskId) {
        return null;
    }

    @Override
    public Data getData(String dataId) {
        return null;
    }

    @Override
    public Boolean hasDataByBindId(String bindId) {
        return null;
    }

    @Override
    public Partner getPartner(String partnerId) {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public Operator getOperator(String operatorId) {
        return null;
    }
}
