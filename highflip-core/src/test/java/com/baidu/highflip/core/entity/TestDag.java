package com.baidu.highflip.core.entity;

import com.baidu.highflip.core.entity.dag.Node;
import highflip.HighflipMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestDag {

    @Test
    public void testGraph() {

    }

    @Test
    public void testNode() {
        var define = HighflipMeta.NodeProto.newBuilder()
                .setName("test_name")
                .setType("test_type")
                .build();

        var node = Node.fromProto(define);
        log.info("node = {}", node);

        // var proto = Node.toProto(node);
        // log.info("proto = {}", proto);
    }
}
