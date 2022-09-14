package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Function;

import java.util.Iterator;

public interface FunctionAdaptor {

    Iterator<Function> listFunction();

    void getFunction(String funcid);
}
