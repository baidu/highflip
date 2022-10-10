package com.baidu.highflip.core.engine;

public interface AdaptorContext {

    Configuration getConfiguration();

    InstanceRegister getRegister();

    HighFlipRuntime getRuntime();
}
