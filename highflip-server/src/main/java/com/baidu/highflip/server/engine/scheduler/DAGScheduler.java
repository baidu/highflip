package com.baidu.highflip.server.engine.scheduler;

import com.baidu.highflip.core.entity.dag.Graph;

public abstract class DAGScheduler {

    public abstract void dispatch(Graph graph);
}
