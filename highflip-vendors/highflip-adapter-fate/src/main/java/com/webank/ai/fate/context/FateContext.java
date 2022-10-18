package com.webank.ai.fate.context;

import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.Configuration;
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

    FateClient client;

    DSLTranslator translator = new DSLTranslator();

    public static FateContext from(InstanceRegister register) {
        FateContext context = new FateContext();

        Configuration config = (Configuration) register.getInstance(InstanceNameList.HIGHFLIP_CONFIGURATION);
        context.setConfig(config);

        return context;
    }
}
