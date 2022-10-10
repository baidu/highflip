package com.baidu.highflip.server.engine.register;

import com.baidu.highflip.core.engine.InstanceRegister;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapRegister implements InstanceRegister {

    Map<String, Object> objects = new ConcurrentHashMap<String, Object>();

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
    public Iterator<String> listNames() {
        return objects.keySet().iterator();
    }
}
