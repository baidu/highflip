package com.baidu.highflip.core.entity;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.dag.Node;
import com.baidu.highflip.core.entity.dag.Party;
import com.baidu.highflip.core.entity.dag.PartyNode;
import com.baidu.highflip.core.entity.dag.codec.TypeValue;
import com.baidu.highflip.core.entity.dag.common.MappedNode;
import highflip.HighflipMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

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

    @Test
    public void testPartyNode() {
        HighflipMeta.PartyProto.PartyNode define = HighflipMeta.PartyProto.PartyNode
                .newBuilder()
                .setName("test_partynode")
                .build();

        var node = PartyNode.fromProto(define);
        log.info("partyNode = {}", node);

        var proto = PartyNode.toProto(node);
        log.info("partyNode = {}", proto);
    }

    HighflipMeta.GraphProto generateGraphProto() {
        HighflipMeta.NodeProto nodeProto1 = HighflipMeta.NodeProto
                .newBuilder()
                .setName("node_1")
                .setType("operator_a")
                .putAttributes("node.name", TypeValue.toProto("hello_node1"))
                .putAttributes("test.level", TypeValue.toProto(2))
                .addInputs(HighflipMeta.NodeInputProto
                        .newBuilder()
                        .setName("input_1")
                        .build())
                .addOutputs(HighflipMeta.NodeOutputProto
                        .newBuilder()
                        .setName("output_1")
                        .build())
                .addOutputs(HighflipMeta.NodeOutputProto
                        .newBuilder()
                        .setName("output_2")
                        .build())
                .build();


        HighflipMeta.NodeProto nodeProto2 = HighflipMeta.NodeProto
                .newBuilder()
                .setName("node_2")
                .setType("operator_b")
                .putAttributes("node.name", TypeValue.toProto("hello_node2"))
                .putAttributes("test.level", TypeValue.toProto(2))
                .addInputs(HighflipMeta.NodeInputProto
                        .newBuilder()
                        .setName("input_1")
                        .setFromNode("node_1")
                        .setFromOutput("output_2")
                        .build())
                .addOutputs(HighflipMeta.NodeOutputProto
                        .newBuilder()
                        .setName("output_1")
                        .build())
                .build();


        HighflipMeta.NodeProto nodeProto3 = HighflipMeta.NodeProto
                .newBuilder()
                .setName("node_3")
                .setType("operator_c")
                .putAttributes("node.name", TypeValue.toProto("hello_node3"))
                .putAttributes("test.level", TypeValue.toProto(2))
                .addInputs(HighflipMeta.NodeInputProto
                        .newBuilder()
                        .setName("input_1")
                        .setFromNode("node_1")
                        .setFromOutput("output_1")
                        .build())
                .addOutputs(HighflipMeta.NodeOutputProto
                        .newBuilder()
                        .setName("output_1")
                        .build())
                .build();

        HighflipMeta.NodeProto nodeProto4 = HighflipMeta.NodeProto
                .newBuilder()
                .setName("node_4")
                .setType("operator_d")
                .putAttributes("node.name", TypeValue.toProto("hello_node3"))
                .putAttributes("test.level", TypeValue.toProto(2))
                .addInputs(HighflipMeta.NodeInputProto
                        .newBuilder()
                        .setName("input_1")
                        .setFromNode("node_2")
                        .setFromOutput("output_1")
                        .build())
                .addInputs(HighflipMeta.NodeInputProto
                        .newBuilder()
                        .setName("input_2")
                        .setFromNode("node_3")
                        .setFromOutput("output_1")
                        .build())
                .addOutputs(HighflipMeta.NodeOutputProto
                        .newBuilder()
                        .setName("output_1")
                        .build())
                .build();

        HighflipMeta.PartyProto partyA = HighflipMeta.PartyProto
                .newBuilder()
                .setName("party_a")
                .putAttributes("party.name", TypeValue.toProto("hello_partyA"))
                .putAttributes("test.level", TypeValue.toProto(3))
                .addNodes(HighflipMeta.PartyProto.PartyNode
                        .newBuilder()
                        .setName("node_2")
                        .putAttributes("node.name", TypeValue.toProto("hello_party_nodeA"))
                        .putAttributes("test.level", TypeValue.toProto(4))
                        .build())
                .build();


        HighflipMeta.PartyProto partyB = HighflipMeta.PartyProto
                .newBuilder()
                .setName("party_b")
                .putAttributes("party.name", TypeValue.toProto("hello_partyB"))
                .putAttributes("test.level", TypeValue.toProto(3))
                .addNodes(HighflipMeta.PartyProto.PartyNode
                        .newBuilder()
                        .setName("node_2")
                        .putAttributes("node.name", TypeValue.toProto("hello_party_nodeB"))
                        .putAttributes("test.level", TypeValue.toProto(4))
                        .build())
                .build();


        HighflipMeta.GraphProto dagProto = HighflipMeta.GraphProto
                .newBuilder()
                .setName("test_dag")
                .putAttributes("graph.name", TypeValue.toProto("hello_graph1"))
                .putAttributes("test.level", TypeValue.toProto(1))
                .addNodes(nodeProto1)
                .addNodes(nodeProto2)
                .addNodes(nodeProto3)
                .addNodes(nodeProto4)
                .addParties(partyA)
                .addParties(partyB)
                .build();

        return dagProto;
    }

    @Test
    public void testDag() {

        HighflipMeta.GraphProto dagProto = generateGraphProto();

        var graph = Graph.fromProto(dagProto);
        log.info("graph = {}", graph);

        List<Node> inputs = graph.getNode("node_4").getInputNodes();
        log.info("inputs = {}", inputs);
        Assertions.assertEquals(2, inputs.size());

        MappedNode mapped = graph.getNode("node_2").getMappedNode("party_a");
        Set<String> keys = mapped.getForwardKeys();
        log.info("mapped key={}", keys);
        Assertions.assertTrue(keys.contains("graph.name"));

        String nodeName = (String) mapped.getForward("node.name", null);
        log.info("mapped node.name={}", nodeName);

        Integer nodeLevel = (Integer) mapped.getForward("test.level", null);
        log.info("mapped test.level={}", nodeLevel);
        Assertions.assertEquals(4, nodeLevel);

        var proto = Graph.toProto(graph);
        log.info("proto = {}", proto);
    }
}
