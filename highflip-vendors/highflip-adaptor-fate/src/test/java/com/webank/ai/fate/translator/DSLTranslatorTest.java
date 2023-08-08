package com.webank.ai.fate.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.baidu.highflip.core.entity.dag.Graph;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.webank.ai.fate.client.FateClient;
import com.webank.ai.fate.client.form.ResultForm;
import com.webank.ai.fate.client.form.job.FateJob;
import com.webank.ai.fate.common.deserializer.SerializerUtils;

import highflip.HighflipMeta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DSLTranslatorTest {

    @Test
    public void translateTest() {
        // step 1: construct a highflip graph
        HighflipMeta.GraphProto graphProto = constructHighflipGraph();

        Graph graph = Graph.fromProto(graphProto);
        // step 2: translate highflip graph to fate dag (dsl and conf)
        DSLTranslator dslTranslator = new DSLTranslator();

        DSLTranslator.FateDAG translate = dslTranslator.translate(graph);

        // fate flow http service
        String TEST_FLOW_URL = "http://10.27.130.41:8380";

        FateClient client = FateClient.connect(TEST_FLOW_URL);

        try {
            log.info("conf = {}",
                     SerializerUtils.toJsonString(translate.getDsl()));
            log.info("dsl = {}",
                     SerializerUtils.toJsonString(translate.getConf()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResultForm<FateJob> job = client.jobSubmit(translate.getDsl(),
                                                   translate.getConf());
        log.info("result = {}", job);
    }

    private HighflipMeta.GraphProto constructHighflipGraph() {
        // reader
        List<highflip.HighflipMeta.NodeOutputProto> readerNodeOutputProtos =
                new ArrayList<>();
        HighflipMeta.NodeOutputProto outputProto =
                HighflipMeta.NodeOutputProto.newBuilder().setName("data")
                                            .setValue(getJsonStringOfDataOutput())
                                            .build();
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
                HighflipMeta.NodeOutputProto.newBuilder().setName("data")
                                            .setValue(getJsonStringOfDataOutput())
                                            .build();
        HighflipMeta.NodeOutputProto dataTransformOutputProto2 =
                HighflipMeta.NodeOutputProto.newBuilder().setName("model")
                                            .setValue(getJsonStringOfModelOutput())
                                            .build();
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
                                      .addAllInputs(dataTransformNodeInputProtos)
                                      .addAllOutputs(dataTransformNodeOutputProtos)
                                      .build();

        // intersect
        List<highflip.HighflipMeta.NodeOutputProto> intersectNodeOutputProtos =
                new ArrayList<>();
        HighflipMeta.NodeOutputProto intersectOutputProto =
                HighflipMeta.NodeOutputProto.newBuilder().setName("data")
                                            .setValue(getJsonStringOfDataOutput())
                                            .build();
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

        // construct guest
        HighflipMeta.PartyProto.PartyNode guestPartyNode1 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(reader.getName())
                                                 .putAttributes("table",
                                                                getGuestPartyReaderNode())
                                                 .build();
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
                HighflipMeta.PartyProto.newBuilder()
                                       .setRole(HighflipMeta.PartyRole.GUEST)
                                       .addAllNodes(guestPartyNodes)
                                       .setName("9999")
                                       .build();

        // construct host
        HighflipMeta.PartyProto.PartyNode hostPartyNode1 =
                HighflipMeta.PartyProto.PartyNode.newBuilder()
                                                 .setName(reader.getName())
                                                 .putAttributes("table",
                                                                getHostReaderTypedValueProto())
                                                 .build();

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
                HighflipMeta.PartyProto.newBuilder()
                                       .setRole(HighflipMeta.PartyRole.HOST)
                                       .addAllNodes(hostPartyNodes)
                                       .setName("10000")
                                       .build();

        // components
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
        tableMap.put("name", HighflipMeta.ValueProto.newBuilder().setString("csv2").build());
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
        hostTableMap.put("name", HighflipMeta.ValueProto.newBuilder().setString("csv1").build());
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

    @Test
    public void testValueProto() {
        Map<String, highflip.HighflipMeta.ValueProto> tableMap2 =
                new HashMap<>();
        tableMap2.put("name", HighflipMeta.ValueProto.newBuilder().setString(
                "csv1").build());
        tableMap2.put("namespace",
                      HighflipMeta.ValueProto.newBuilder().setString("HIGH-FLIP").build());

        Map<String, highflip.HighflipMeta.ValueProto> tableMap = new HashMap<>();
        tableMap.put("name", HighflipMeta.ValueProto.newBuilder().setString("csv1").build());
        tableMap.put("namespace", HighflipMeta.ValueProto.newBuilder().setString("HIGH-FLIP").build());
        tableMap.put("bo",
                     HighflipMeta.ValueProto.newBuilder().setBool(true).build());

        HighflipMeta.MapProto mapProto2 =  HighflipMeta.MapProto.newBuilder().putAllMap(tableMap2).build();
        tableMap.put("mp",
                     HighflipMeta.ValueProto.newBuilder().setMap(mapProto2).build());

        List<highflip.HighflipMeta.ValueProto> list = new ArrayList<>();
        list.add(HighflipMeta.ValueProto.newBuilder().setMap(mapProto2).build());

        HighflipMeta.ListProto listProto =
                HighflipMeta.ListProto.newBuilder().addAllList(list).build();

        tableMap.put("list",
                     HighflipMeta.ValueProto.newBuilder().setList(listProto).build());

        HighflipMeta.MapProto mapProto =
                HighflipMeta.MapProto.newBuilder().putAllMap(tableMap).build();
        HighflipMeta.ValueProto valueProto =
                HighflipMeta.ValueProto.newBuilder().setMap(mapProto).build();

        highflip.HighflipMeta.TypedValueProto typedValueProto =
                highflip.HighflipMeta.TypedValueProto.newBuilder().setTypeValue(
                        HighflipMeta.TypedValueProto.TypeProto.MAP_VALUE).setValue(valueProto).build();

        HighflipMeta.MapProto map = typedValueProto.getValue().getMap();
        Map<String, Object> map1 = mapProtoConvert2Map(map);
        try{
            String s = SerializerUtils.toJsonString(map1);
            System.out.println(s);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> mapProtoConvert2Map(HighflipMeta.MapProto mapProto){
        Map<String, Object> result = new HashMap<>();
        Map<String, HighflipMeta.ValueProto> map = mapProto.getMapMap();
        for(Map.Entry<String, HighflipMeta.ValueProto> entry : map.entrySet()) {
            String key = entry.getKey();
            HighflipMeta.ValueProto value = entry.getValue();
            result.put(key, convertValueProto(value));
        }
        return result;
    }

    private Object convertValueProto(HighflipMeta.ValueProto value){
        switch (value.getValueCase()) {
            case BOOL:
                return value.getBool();
            case INT:
                return value.getInt();
            case LONG:
                return value.getLong();
            case FLOAT:
                return value.getFloat();
            case DOUBLE:
                return value.getDouble();
            case STRING:
                return value.getString();
            case BYTES:
                return value.getBytes();
            case LIST:
                List<HighflipMeta.ValueProto> list =
                        value.getList().getListList();
                List<Object> resultList = new ArrayList<>();
                for(HighflipMeta.ValueProto listValue: list) {
                    resultList.add(convertValueProto(listValue));
                }
                return resultList;
            case MAP:
                //                Map<String, Object>
                return mapProtoConvert2Map(value.getMap());
            default:
                throw new RuntimeException("NOT_SUPPORTED_TYPE");
        }
    }

}
