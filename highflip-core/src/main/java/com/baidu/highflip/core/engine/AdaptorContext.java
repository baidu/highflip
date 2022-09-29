package com.baidu.highflip.core.engine;

public interface AdaptorContext {

    Configuration getConfiguration();

    HighFlipRegister getRegister();

    EngineRuntime getRuntime();
}
