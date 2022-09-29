package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Operator;

public interface OperatorAdaptor {

    int getOperatorCount();

    Operator getOperatorByIndex(int index, Operator operator);

    Operator updateOperator(Operator operator);

    boolean hasOperator(Operator operator);

    void deleteOperator(Operator operator);
}
