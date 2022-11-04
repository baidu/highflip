package com.webank.ai.fate;

import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.webank.ai.fate.adaptor.DataAdaptor;
import com.webank.ai.fate.adaptor.JobAdaptor;
import com.webank.ai.fate.adaptor.TaskAdaptor;
import com.webank.ai.fate.context.FateContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class FateAdaptor implements HighFlipAdaptor {

    FateContext context;

    @Override
    public void setup(InstanceRegister register) {
        setContext(FateContext.from(register));

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new JobAdaptor(getContext()));
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new TaskAdaptor(getContext()));
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_DATA, new DataAdaptor(getContext()));
    }

    @Override
    public void clean(InstanceRegister register) {

    }
}
