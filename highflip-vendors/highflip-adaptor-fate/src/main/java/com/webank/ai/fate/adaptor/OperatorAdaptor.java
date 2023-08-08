package com.webank.ai.fate.adaptor;

import com.baidu.highflip.core.entity.runtime.Operator;

public class OperatorAdaptor implements com.baidu.highflip.core.adaptor.OperatorAdaptor {
    @Override
    public int getOperatorCount() {
        return 0;
    }

    @Override
    public Operator getOperatorByIndex(int index, Operator operator) {
        return null;
    }

    @Override
    public Operator updateOperator(Operator operator) {
        return null;
    }

    @Override
    public boolean hasOperator(Operator operator) {
        return false;
    }

    @Override
    public void deleteOperator(Operator operator) {

    }
}
