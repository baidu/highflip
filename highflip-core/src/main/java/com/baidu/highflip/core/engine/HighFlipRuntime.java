package com.baidu.highflip.core.engine;

import com.baidu.highflip.core.entity.runtime.Operator;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.User;

public interface HighFlipRuntime {

    Job getJob(String jobId);

    Task getTask(String taskId);

    Data getData(String dataId);

    Partner getPartner(String partnerId);

    User getUser(String userId);

    Operator getAlgorithm(String algId);
}
