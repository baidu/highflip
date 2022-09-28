package com.baidu.highflip.server.engine.component;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Component
public class HighFlipRegister implements com.baidu.highflip.core.engine.HighFlipRegister {

    Map<String, Object> objects = new TreeMap<>();

    @Override
    public Object getInstance(String name) {
        return objects.getOrDefault(name, null);
    }

    @Override
    public void register(String name, Object object) {
        objects.put(name, object);
    }

    @Override
    public Object revoke(String name) {
        return objects.remove(name);
    }

    @Override
    public Iterator<String> listInstance() {
        return objects.keySet().iterator();
    }
}
