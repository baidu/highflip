package com.baidu.highflip.core.engine;

public interface AdaptorContext {

    Configuration getConfiguration();

    Object getInstance(String name);

    void register(String name, Object object);

    Object revoke(String name);
}
