package com.baidu.highflip.server.engine.register;

import com.baidu.highflip.core.engine.InstanceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Iterator;

public class BeanRegister implements InstanceRegister {

    @Autowired
    ApplicationContext context;


    @Override
    public Object getInstance(String name) {
        return context.getBean(name);
    }

    @Override
    public void register(String name, Object object) {

    }

    @Override
    public Object revoke(String name) {
        return null;
    }

    @Override
    public Iterator<String> listNames() {
        return Arrays.stream(context.getBeanDefinitionNames()).iterator();
    }
}
