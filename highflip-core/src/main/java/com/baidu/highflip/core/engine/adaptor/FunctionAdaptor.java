package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Function;

import java.util.Iterator;

public interface FunctionAdaptor {

    Iterator<Function> listFunction();

    int getCount();

    Function getFunctionByIndex(int index);

    boolean hasFunction(Function func);

    void deleteFunction(Function func);
}
