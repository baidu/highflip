package com.baidu.highflip.server.engine.translator;

import com.baidu.highflip.core.entity.dag.Graph;

public abstract class AbstractTranslator {

    public abstract Object translate(Graph dag);
}
