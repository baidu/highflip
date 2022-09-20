package com.baidu.highflip.core.engine.adaptor;

public interface AdaptorContext {

    PlatformAdaptor getPlatformAdaptor();

    DataAdaptor getDataAdaptor();

    FunctionAdaptor getFunctionAdaptor();

    JobAdaptor getJobAdaptor();

    TaskAdaptor getTaskAdaptor();

    PartnerAdaptor getPartnerAdaptor();

}
