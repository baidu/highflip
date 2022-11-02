package com.baidu.highflip.core.engine;

import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Operator;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.User;

public interface HighFlipRuntime {

    Configuration getConfiguration();

    Job getJob(String jobId);

    Boolean hasJobByBindId(String bindId);

    Task getTask(String taskId);

    Data getData(String dataId);

    Boolean hasDataByBindId(String bindId);

    Partner getPartner(String partnerId);

    User getUser(String userId);

    Operator getOperator(String operatorId);
}
