package com.baidu.highflip.core.entity;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.dag.Node;
import com.baidu.highflip.core.entity.dag.Party;
import com.baidu.highflip.core.entity.dag.PartyNode;
import com.baidu.highflip.core.entity.dag.codec.TypeValue;
import com.baidu.highflip.core.entity.dag.common.MappedNode;
import com.baidu.highflip.core.utils.SerializerUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import highflip.HighflipMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class TestDag {

    @Test
    public void testGraph() {
        HighflipMeta.GraphProto define = constructHighflipGraph();

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

    private HighflipMeta.GraphProto constructHighflipGraph() {
        // reader
        List<highflip.HighflipMeta.NodeOutputProto> readerNodeOutputProtos =
                new ArrayList<>();

        HighflipMeta.NodeOutputProto outputProto =
                HighflipMeta.NodeOutputProto.newBuilder().setName("data").setValue(getJsonStringOfDataOutput()).build();
        readerNodeOutputProtos.add(outputProto);

        HighflipMeta.NodeProto reader =
                HighflipMeta.NodeProto.newBuilder().setName("reader_0")
                                      .setType("Reader")
                                      .addAllOutputs(readerNodeOutputProtos)
                                      .build();

        // DataTransform
        List<highflip.HighflipMeta.NodeOutputProto> dataTransformNodeOutputProtos =
                new ArrayList<>();

        HighflipMeta.NodeOutputProto dataTransformOutputProto =
                HighflipMeta.NodeOutputProto.newBuilder().setName("data").setValue(getJsonStringOfDataOutput()).build();
        HighflipMeta.NodeOutputProto dataTransformOutputProto2 =
                HighflipMeta.NodeOutputProto.newBuilder().setName("model").setValue(getJsonStringOfModelOutput()).build();
        dataTransformNodeOutputProtos.add(dataTransformOutputProto);
        dataTransformNodeOutputProtos.add(dataTransformOutputProto2);



        List<highflip.HighflipMeta.NodeInputProto> dataTransformNodeInputProtos =
                new ArrayList<>();
        String json = getDataTransformInputJsonString();
        HighflipMeta.NodeInputProto dataTransformInputProto =
                HighflipMeta.NodeInputProto.newBuilder()
                                           .setName("data")
                                           .setValue(json)
                                           .build();


        dataTransformNodeInputProtos.add(dataTransformInputProto);

        HighflipMeta.NodeProto dataTransform =
                HighflipMeta.NodeProto.newBuilder().setName("data_transform_0")
                                      .setType("DataTransform")
                                      .addAllInputs(
                                              dataTransformNodeInputProtos)
                                      .addAllOutputs(
                                              dataTransformNodeOutputProtos)
                                      .build();

        // intersect
        List<highflip.HighflipMeta.NodeOutputProto> intersectNodeOutputProtos =
                new ArrayList<>();
        HighflipMeta.NodeOutputProto intersectOutputProto =
                HighflipMeta.NodeOutputProto.newBuilder().setName("data").setValue(getJsonStringOfDataOutput()).build();
        intersectNodeOutputProtos.add(intersectOutputProto);

        List<highflip.HighflipMeta.NodeInputProto> intersectNodeInputProtos =
                new ArrayList<>();

        String dataTransformInputJsonString = getIntersectionInputJsonString();
        HighflipMeta.NodeInputProto intersectInputProto =
                HighflipMeta.NodeInputProto.newBuilder()
                                           .setName("data")
                                           .setValue(dataTransformInputJsonString)
                                           .build();

        intersectNodeInputProtos.add(intersectInputProto);

        HighflipMeta.NodeProto intersect =
                HighflipMeta.NodeProto.newBuilder().setName("intersect_0")
                                      .setType("Intersection")
                                      .addAllInputs(intersectNodeInputProtos)
                                      .addAllOutputs(intersectNodeOutputProtos)
                                      .build();


        //        Map<String, highflip.HighflipMeta.ValueProto> tableMap = new HashMap<>();
        //        tableMap.put("name", HighflipMeta.ValueProto.newBuilder().setString("csv1").build());
        //        tableMap.put("namespace", HighflipMeta.ValueProto.newBuilder().setString("HIGH-FLIP").build());
        //        HighflipMeta.MapProto mapProto =
        //                HighflipMeta.MapProto.newBuilder().putAllMap(tableMap).build();
        //        HighflipMeta.ValueProto valueProto =
        //                HighflipMeta.ValueProto.newBuilder().setMap(mapProto).build();
        //        highflip.HighflipMeta.TypedValueProto typedValueProto =
        //                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
        //                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(valueProto).build();

        HighflipMeta.PartyProto.PartyNode guestPartyNode1 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(reader.getName())
                                                 .putAttributes("table",
                                                                getGuestPartyReaderNode())
                                                 .build();
        //
        HighflipMeta.TypedValueProto withLabel=
                HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                                    HighflipMeta.TypedValueProto.TypeProto.BOOLEAN_VALUE)
                                            .setValue(HighflipMeta.ValueProto.newBuilder()
                                                                             .setBool(false)
                                                                             .build()).build();

        HighflipMeta.TypedValueProto outputFormat=
                HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                                    HighflipMeta.TypedValueProto.TypeProto.STRING_VALUE)
                                            .setValue(HighflipMeta.ValueProto.newBuilder()
                                                                             .setString("dense")
                                                                             .build()).build();

        HighflipMeta.PartyProto.PartyNode guestPartyNode2 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(dataTransform.getName())
                                                 .putAttributes("with_label",withLabel)
                                                 .putAttributes("output_format",outputFormat)
                                                 .build();
        // common conf
        /*

        "intersect_0": {
                "intersect_method": "rsa",
                "sync_intersect_ids": false,
                "only_output_key": true,
                "rsa_params": {
                    "hash_method": "sha256",
                    "final_hash_method": "sha256",
                    "split_calculation": false,
                    "key_length": 2048
                }
            }
         */
        HighflipMeta.TypedValueProto intersectMethod=
                HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                                    HighflipMeta.TypedValueProto.TypeProto.STRING_VALUE)
                                            .setValue(HighflipMeta.ValueProto.newBuilder()
                                                                             .setString("rsa")
                                                                             .build()).build();
        HighflipMeta.TypedValueProto syncIntersectIds=
                HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                                    HighflipMeta.TypedValueProto.TypeProto.BOOLEAN_VALUE)
                                            .setValue(HighflipMeta.ValueProto.newBuilder()
                                                                             .setBool(false)
                                                                             .build()).build();
        HighflipMeta.TypedValueProto onlyOutputKey=
                HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                                    HighflipMeta.TypedValueProto.TypeProto.BOOLEAN_VALUE)
                                            .setValue(HighflipMeta.ValueProto.newBuilder()
                                                                             .setBool(true)
                                                                             .build()).build();

        //        Map<String, highflip.HighflipMeta.ValueProto> commonTableMap =
        //                new HashMap<>();

        //        tableMap.put("hash_method", HighflipMeta.ValueProto.newBuilder().setString("sha256").build());
        //        tableMap.put("final_hash_method", HighflipMeta.ValueProto.newBuilder().setString("sha256").build());
        //        tableMap.put("split_calculation", HighflipMeta.ValueProto.newBuilder().setBool(false).build());
        //        tableMap.put("key_length", HighflipMeta.ValueProto.newBuilder().setInt(2048).build());




        //        Map<String, highflip.HighflipMeta.ValueProto> commonRsaParamConfMap =
        //                new HashMap<>();
        //        commonRsaParamConfMap.put("hash_method",
        //                                  HighflipMeta.ValueProto.newBuilder().setString("sha256").build());
        //        commonRsaParamConfMap.put("final_hash_method",  HighflipMeta.ValueProto.newBuilder().setString("sha256").build());
        //        commonRsaParamConfMap.put("split_calculation",
        //                                  HighflipMeta.ValueProto.newBuilder().setBool(
        //                                          false).build());
        //        commonRsaParamConfMap.put("key_length",
        //                                  HighflipMeta.ValueProto.newBuilder().setInt(2048).build());
        //        HighflipMeta.MapProto commonValueMapProto =
        //                HighflipMeta.MapProto.newBuilder().putAllMap(commonRsaParamConfMap).build();
        //        HighflipMeta.ValueProto commonValueProto =
        //                HighflipMeta.ValueProto.newBuilder().setMap(commonValueMapProto).build();
        //        highflip.HighflipMeta.TypedValueProto commonTypedValueProto =
        //                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
        //                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(commonValueProto).build();

        HighflipMeta.PartyProto.PartyNode commonPartyNode3 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(intersect.getName())
                                                 .putAttributes("intersect_method",intersectMethod)
                                                 .putAttributes("sync_intersect_ids",syncIntersectIds)
                                                 .putAttributes("only_output_key",onlyOutputKey)
                                                 .putAttributes("rsa_params",
                                                                getCommonConfRsaParamTypedValueProto())
                                                 .build();

        List<HighflipMeta.PartyProto.PartyNode> guestPartyNodes =
                new ArrayList<>();
        guestPartyNodes.add(guestPartyNode1);
        guestPartyNodes.add(guestPartyNode2);
        guestPartyNodes.add(commonPartyNode3);

        HighflipMeta.PartyProto guest =
                HighflipMeta.PartyProto.newBuilder().setRole(
                        HighflipMeta.PartyRole.GUEST).addAllNodes(guestPartyNodes).setName("9999").build();
        // guest 已经组装好

        // 开始组装host

        //        Map<String, highflip.HighflipMeta.ValueProto> hostTableMap =
        //                new HashMap<>();
        //        hostTableMap.put("name",HighflipMeta.ValueProto.newBuilder().setString("csv2").build());
        //        hostTableMap.put("namespace", HighflipMeta.ValueProto.newBuilder().setString("HIGH-FLIP").build());
        //        HighflipMeta.MapProto hostMapProto =
        //                HighflipMeta.MapProto.newBuilder().putAllMap(hostTableMap).build();
        //        HighflipMeta.ValueProto hostValueProto =
        //                HighflipMeta.ValueProto.newBuilder().setMap(hostMapProto).build();
        //        highflip.HighflipMeta.TypedValueProto hostTypedValueProto =
        //                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
        //                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(hostValueProto).build();

        HighflipMeta.PartyProto.PartyNode hostPartyNode1 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(reader.getName())
                                                 .putAttributes("table",
                                                                getHostReaderTypedValueProto())
                                                 .build();

        //
        HighflipMeta.PartyProto.PartyNode hostPartyNode2 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(dataTransform.getName())
                                                 .putAttributes("with_label",withLabel)
                                                 .putAttributes("output_format",outputFormat)
                                                 .build();
        List<HighflipMeta.PartyProto.PartyNode> hostPartyNodes =
                new ArrayList<>();
        hostPartyNodes.add(hostPartyNode1);
        hostPartyNodes.add(hostPartyNode2);
        hostPartyNodes.add(commonPartyNode3);

        HighflipMeta.PartyProto host =
                HighflipMeta.PartyProto.newBuilder().setRole(
                        HighflipMeta.PartyRole.HOST).addAllNodes(hostPartyNodes).setName("10000").build();

        List<highflip.HighflipMeta.NodeProto> node = new ArrayList<>();
        node.add(reader);
        node.add(dataTransform);
        node.add(intersect);

        List<HighflipMeta.PartyProto> partyProtoList = new ArrayList<>();
        partyProtoList.add(guest);
        partyProtoList.add(host);

        HighflipMeta.GraphProto graphProto =
                HighflipMeta.GraphProto.newBuilder().setName("jobId-202301")
                                       .addAllNodes(node)
                                       .addAllParties(partyProtoList).build();

        return graphProto;
    }

    private String getIntersectionInputJsonString() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> data = new ArrayList<>();
        data.add("data_transform_0.data");
        map.put("data", data);
        try {
            return SerializerUtils.toJsonString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDataTransformInputJsonString() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> data = new ArrayList<>();
        data.add("reader_0.data");
        map.put("data", data);
        try {
            return SerializerUtils.toJsonString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getJsonStringOfDataOutput() {
        List<String> data = new ArrayList<>();
        data.add("data");
        try {
            return SerializerUtils.toJsonString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getJsonStringOfModelOutput() {
        List<String> data = new ArrayList<>();
        data.add("model");
        try {
            return SerializerUtils.toJsonString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HighflipMeta.TypedValueProto getGuestPartyReaderNode() {
        Map<String, highflip.HighflipMeta.ValueProto> tableMap = new HashMap<>();
        tableMap.put("name", HighflipMeta.ValueProto.newBuilder().setString("csv1").build());
        tableMap.put("namespace", HighflipMeta.ValueProto.newBuilder().setString("HIGH-FLIP").build());
        HighflipMeta.MapProto mapProto =
                HighflipMeta.MapProto.newBuilder().putAllMap(tableMap).build();
        HighflipMeta.ValueProto valueProto =
                HighflipMeta.ValueProto.newBuilder().setMap(mapProto).build();
        highflip.HighflipMeta.TypedValueProto typedValueProto =
                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(valueProto).build();

        return typedValueProto;
    }

    private HighflipMeta.TypedValueProto getCommonConfRsaParamTypedValueProto(){
        Map<String, highflip.HighflipMeta.ValueProto> commonRsaParamConfMap =
                new HashMap<>();
        commonRsaParamConfMap.put("hash_method",
                                  HighflipMeta.ValueProto.newBuilder().setString("sha256").build());
        commonRsaParamConfMap.put("final_hash_method",  HighflipMeta.ValueProto.newBuilder().setString("sha256").build());
        commonRsaParamConfMap.put("split_calculation",
                                  HighflipMeta.ValueProto.newBuilder().setBool(
                                          false).build());
        commonRsaParamConfMap.put("key_length",
                                  HighflipMeta.ValueProto.newBuilder().setInt(2048).build());
        HighflipMeta.MapProto commonValueMapProto =
                HighflipMeta.MapProto.newBuilder().putAllMap(commonRsaParamConfMap).build();
        HighflipMeta.ValueProto commonValueProto =
                HighflipMeta.ValueProto.newBuilder().setMap(commonValueMapProto).build();
        highflip.HighflipMeta.TypedValueProto commonTypedValueProto =
                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(commonValueProto).build();
        return commonTypedValueProto;
    }

    private HighflipMeta.TypedValueProto getHostReaderTypedValueProto() {
        Map<String, highflip.HighflipMeta.ValueProto> hostTableMap =
                new HashMap<>();
        hostTableMap.put("name",HighflipMeta.ValueProto.newBuilder().setString("csv2").build());
        hostTableMap.put("namespace", HighflipMeta.ValueProto.newBuilder().setString("HIGH-FLIP").build());
        HighflipMeta.MapProto hostMapProto =
                HighflipMeta.MapProto.newBuilder().putAllMap(hostTableMap).build();
        HighflipMeta.ValueProto hostValueProto =
                HighflipMeta.ValueProto.newBuilder().setMap(hostMapProto).build();
        highflip.HighflipMeta.TypedValueProto hostTypedValueProto =
                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(hostValueProto).build();
        return hostTypedValueProto;
    }

}
