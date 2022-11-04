package com.webank.ai.fate.adaptor;

import com.webank.ai.fate.context.FateContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class TaskAdaptor {

    FateContext context;

    public TaskAdaptor(FateContext context) {
        this.context = context;
    }
}
