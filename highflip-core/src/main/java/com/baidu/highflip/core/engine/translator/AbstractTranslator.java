package com.baidu.highflip.core.engine.translator;

import com.baidu.highflip.core.entity.dag.Graph;

import java.util.Map;

public interface AbstractTranslator {

    Map<String, Object> translate(Graph dag);
}
