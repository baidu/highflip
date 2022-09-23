package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.engine.AdaptorContext;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.util.Set;

@Slf4j
public abstract class AbstractAdaptor implements Closeable {

    private AdaptorContext context;

    public void initialize(AdaptorContext context) {
        this.context = context;
    }

    @Override
    public void close() {

    }

    public AdaptorContext getAdaptorContext() {
        return context;
    }

    public abstract String getName();

    public abstract Set<String> getFeatures();
}
