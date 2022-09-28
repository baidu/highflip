package com.baidu.highflip.adaptor;

import com.baidu.highflip.adaptor.demo.DumbJobAdaptor;
import com.baidu.highflip.adaptor.demo.DumbTaskAdaptor;
import com.baidu.highflip.core.engine.AdaptorContext;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.HighFlipRegister;

public class DemoAdaptor implements HighFlipAdaptor {
    @Override
    public void setup(HighFlipRegister register) {
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new DumbJobAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new DumbTaskAdaptor());
    }

    @Override
    public void clean(HighFlipRegister register) {

    }
}
