package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Algorithm;

public interface AlgorithmAdaptor {

    int getAlgorithmCount();

    Algorithm getAlgorithmByIndex(int index, Algorithm alg);

    Algorithm updateAlgorithm(Algorithm alg);

    boolean hasAlgorithm(Algorithm alg);

    void deleteAlgorithm(Algorithm alg);
}
