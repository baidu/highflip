package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.engine.translator.AbstractTranslator;

public interface AdaptorContext {

    PlatformAdaptor getPlatformAdaptor();

    DataAdaptor getDataAdaptor();

    FunctionAdaptor getFunctionAdaptor();

    JobAdaptor getJobAdaptor();

    TaskAdaptor getTaskAdaptor();

    PartnerAdaptor getPartnerAdaptor();

    AbstractTranslator getJobTranslator();
}
