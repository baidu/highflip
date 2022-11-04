package com.webank.ai.fate.translator;

import com.baidu.highflip.core.entity.dag.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class DSLTranslator {

    public FateDAG translate(Graph dag) {
        return new FateDAG();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FateDAG {

        String dsl;

        String conf;
    }
}
