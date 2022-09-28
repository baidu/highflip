package com.webank.ai.fate;

import com.baidu.highflip.core.engine.AdaptorContext;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.common.InstanceNameList;
import com.webank.ai.fate.adaptor.JobAdaptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FateMain implements HighFlipAdaptor {

    @Override
    public void setup(AdaptorContext context){
        context.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new JobAdaptor());
    }

    @Override
    public void clean(AdaptorContext context) {

    }
}
