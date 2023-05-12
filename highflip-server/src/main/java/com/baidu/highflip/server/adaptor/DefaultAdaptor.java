package com.baidu.highflip.server.adaptor;

import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.server.adaptor.impl.ConfigurableOperatorAdaptor;
import com.baidu.highflip.server.adaptor.impl.ConfigurablePartnerAdaptor;
import com.baidu.highflip.server.adaptor.impl.ConfigurablePlatformAdaptor;
import com.baidu.highflip.server.adaptor.impl.ConfigurableServiceAdaptor;
import com.baidu.highflip.server.adaptor.impl.DumbJobAdaptor;
import com.baidu.highflip.server.adaptor.impl.DumbTaskAdaptor;
import com.baidu.highflip.server.adaptor.impl.FixedSingleDataAdaptor;
import com.baidu.highflip.server.adaptor.impl.FixedSingleUserAdaptor;
import com.baidu.highflip.server.utils.HighFlipUtils;

public class DefaultAdaptor implements HighFlipAdaptor {

    public static final String USER_PROPERTIES = "/adaptor/highflip.user.properties";

    public static final String PARTNER_PROPERTIES = "/adaptor/highflip.partner.properties";

    public static final String OPERATOR_PROPERTIES = "/adaptor/highflip.operator.properties";

    public static final String PLATFORM_PROPERTIES = "/adaptor/highflip.platform.properties";

    /**
     * 用于配置本侧服务的配置信息
     */
    public static final String SERVICE_PROPERTIES = "/adaptor/highflip.service.properties";

    @Override
    public void setup(InstanceRegister register) {
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_JOB, new DumbJobAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_TASK, new DumbTaskAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_DATA, new FixedSingleDataAdaptor());
        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_USER, new FixedSingleUserAdaptor());

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_OPERATOR,
                new ConfigurableOperatorAdaptor());

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_PARTNER,
                new ConfigurablePartnerAdaptor(HighFlipUtils.getProperty(PARTNER_PROPERTIES)));

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_PLATFORM,
                new ConfigurablePlatformAdaptor(HighFlipUtils.getProperty(PLATFORM_PROPERTIES)));

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR_SERVICE,
                          new ConfigurableServiceAdaptor(HighFlipUtils.getProperty(SERVICE_PROPERTIES)));
    }

    @Override
    public void clean(InstanceRegister register) {

    }
}
