package com.webank.ai.fate;

import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.webank.ai.fate.adaptor.JobAdaptor;
import com.webank.ai.fate.adaptor.TaskAdaptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FateAdaptor implements HighFlipAdaptor {

    @Override
    public void setup(InstanceRegister register){
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new JobAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new TaskAdaptor());
    }

    @Override
    public void clean(InstanceRegister register) {

    }
}
