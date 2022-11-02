package com.baidu.highflip.server.adapter;

import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.server.adapter.impl.DumbJobAdaptor;
import com.baidu.highflip.server.adapter.impl.DumbTaskAdaptor;
import com.baidu.highflip.server.adapter.impl.FixedSingleDataAdaptor;
import com.baidu.highflip.server.adapter.impl.FixedSingleUserAdaptor;

public class DefaultAdaptor implements HighFlipAdaptor {

    @Override
    public void setup(InstanceRegister register) {
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new DumbJobAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new DumbTaskAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_DATA, new FixedSingleDataAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_USER, new FixedSingleUserAdaptor());
    }

    @Override
    public void clean(InstanceRegister register) {

    }
}
