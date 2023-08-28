package com.webank.ai.fate.context;

import com.baidu.highflip.core.adaptor.ServiceAdaptor;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.Configuration;
import com.baidu.highflip.core.engine.HighFlipRuntime;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.webank.ai.fate.client.FateClient;
import com.webank.ai.fate.translator.DSLTranslator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FateContext {

    Configuration config;

    HighFlipRuntime highFlipRuntime;

    FateClient client;

    DSLTranslator translator = new DSLTranslator();

    public static FateContext from(InstanceRegister register) {
        FateContext context = new FateContext();

        Configuration config = (Configuration) register.getInstance(InstanceNameList.HIGHFLIP_CONFIGURATION);
        HighFlipRuntime highFlipRuntime =
                (HighFlipRuntime) register.getInstance(InstanceNameList.HIGHFLIP_RUNTIME);
        context.setConfig(config);
        context.setHighFlipRuntime(highFlipRuntime);
        ServiceAdaptor serviceAdaptor =
                (ServiceAdaptor) register.getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_SERVICE);
        log.info("connecting to fate flow http service:{}", serviceAdaptor.getUrl());
        context.setClient(FateClient.connect(serviceAdaptor.getUrl()));
        context.getTranslator().setPartyId(serviceAdaptor.getPartyId());
        context.getTranslator().setRole(serviceAdaptor.getRole());
        return context;
    }
}
