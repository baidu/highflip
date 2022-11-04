package com.baidu.highflip.adaptor;

import com.baidu.highflip.adaptor.demo.DemoDataAdaptor;
import com.baidu.highflip.adaptor.demo.DemoJobAdaptor;
import com.baidu.highflip.adaptor.demo.DemoOperatorAdaptor;
import com.baidu.highflip.adaptor.demo.DemoTaskAdaptor;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.engine.InstanceRegister;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoAdaptor implements HighFlipAdaptor {
    @Override
    public void setup(InstanceRegister register) {
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new DemoJobAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new DemoTaskAdaptor());

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_DATA, new DemoDataAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_OPERATOR, new DemoOperatorAdaptor());
    }

    @Override
    public void clean(InstanceRegister register) {

    }
}
