package com.baidu.highflip.adaptor;

import com.baidu.highflip.adaptor.dumb.DumbJobAdaptor;
import com.baidu.highflip.adaptor.dumb.DumbTaskAdaptor;
import com.baidu.highflip.core.engine.AdaptorContext;
import com.baidu.highflip.core.engine.AdaptorMain;
import com.baidu.highflip.core.common.InstanceNameList;

public class DumpMain implements AdaptorMain {
    @Override
    public void setup(AdaptorContext context) {
        context.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new DumbJobAdaptor());
        context.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new DumbTaskAdaptor());
    }

    @Override
    public void clean(AdaptorContext context) {

    }
}
