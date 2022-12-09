package com.baidu.highflip.core.entity;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.dag.Node;
import com.baidu.highflip.core.entity.dag.Party;
import highflip.HighflipMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestDag {


    @Test
    public void testGraph() {
        HighflipMeta.GraphProto define = HighflipMeta.GraphProto
                .newBuilder()
                .setName("test_graph")
                .build();

        var graph = Graph.fromProto(define);
        log.info("graph = {}", graph);

        var proto = Graph.toProto(graph);
        log.info("proto = {}", proto);
    }

    @Test
    public void testNode() {

        HighflipMeta.NodeProto define = HighflipMeta.NodeProto.newBuilder()
                .setName("test_name")
                .setType("test_type")
                .build();

        var node = Node.fromProto(define);
        log.info("node = {}", node);

        var proto = Node.toProto(node);
        log.info("proto = {}", proto);
    }


    @Test
    public void testParty() {
        HighflipMeta.PartyProto define = HighflipMeta.PartyProto.newBuilder()
                .setName("test_party")
                .setRole(HighflipMeta.PartyRole.HOST)
                .build();

        var party = Party.fromProto(define);
        log.info("party = {}", party);

        var proto = Party.toProto(party);
        log.info("proto = {}", proto);
    }
}
