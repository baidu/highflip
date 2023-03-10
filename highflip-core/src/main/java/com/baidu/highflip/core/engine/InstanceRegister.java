package com.baidu.highflip.core.engine;

import java.util.Iterator;

public interface InstanceRegister {

    Object getInstance(String name);

    void register(String name, Object object);

    Object revoke(String name);

    Iterator<String> listNames();
}

