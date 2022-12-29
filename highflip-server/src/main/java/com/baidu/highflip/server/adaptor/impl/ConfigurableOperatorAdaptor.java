package com.baidu.highflip.server.adaptor.impl;

import com.baidu.highflip.core.adaptor.OperatorAdaptor;
import com.baidu.highflip.core.entity.runtime.Operator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigurableOperatorAdaptor implements OperatorAdaptor {

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
