package com.baidu.highflip.server.engine.register;

import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.core.utils.Foreach;

import java.util.*;

public class CombinedRegister implements InstanceRegister {

    LinkedList<InstanceRegister> registers = new LinkedList<>();

    public CombinedRegister(InstanceRegister writable, InstanceRegister... readables) {
        registers.add(writable);
        registers.addAll(List.of(readables));
    }

    public void setWritable(InstanceRegister register) {
        registers.remove(register);
        registers.addFirst(register);
    }

    @Override
    public Object getInstance(String name) {
        for (InstanceRegister register : registers) {
            Object obj = register.getInstance(name);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public void register(String name, Object object) {
        registers.getFirst()
                .register(name, object);
    }

    @Override
    public Object revoke(String name) {
        Object obj = null;
        for (InstanceRegister register : registers) {
            if (obj == null) {
                obj = register.revoke(name);
            } else {
                register.revoke(name);
            }
        }
        return obj;
    }

    @Override
    public Iterator<String> listNames() {
        Set<String> names = new TreeSet<>();
        for (InstanceRegister register : registers) {
            for (String name : Foreach.from(register.listNames())) {
                names.add(name);
            }
        }
        return names.iterator();
    }
}
