package com.baidu.highflip.server.engine.component;

import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class HighFlipRegister implements com.baidu.highflip.core.engine.HighFlipRegister {

    @Override
    public Object getInstance(String name) {
        return null;
    }

    @Override
    public void register(String name, Object object) {

    }

    @Override
    public Object revoke(String name) {
        return null;
    }

    @Override
    public Iterator<String> listInstance() {
        return null;
    }
}
