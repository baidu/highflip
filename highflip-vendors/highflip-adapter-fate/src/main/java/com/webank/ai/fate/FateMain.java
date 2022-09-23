package com.webank.ai.fate;

import com.baidu.highflip.core.engine.AdaptorContext;
import com.baidu.highflip.core.engine.AdaptorMain;
import com.baidu.highflip.core.common.InstanceNameList;
import com.webank.ai.fate.adaptor.JobAdaptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FateMain implements AdaptorMain {

    @Override
    public void setup(AdaptorContext context){
        context.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new JobAdaptor());
    }

    @Override
    public void clean(AdaptorContext context) {

    }
}
