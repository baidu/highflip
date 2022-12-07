package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import highflip.HighflipMeta;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Node extends AttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710002L;

    String name;

    String type;

    String description;

    Map<String, String> inputs;

    Map<String, String> outputs;

    public static Node fromProto(HighflipMeta.NodeProto proto) {
        Node n = new Node();
        n.setName(proto.getName());
        n.setDescription(proto.getDescription());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        return n;
    }

    public static HighflipMeta.NodeProto toProto(Node node) {
        HighflipMeta.NodeProto proto = HighflipMeta.NodeProto
                .newBuilder()
                .setName(node.getName())
                .setDescription(node.getDescription())
                .setType(node.getType())
                .putAllAttributes(AttributeMap.toProto(node.getAttributes()))
                .addAllInputs(node.getInputs()
                        .entrySet()
                        .stream()
                        .map(n -> HighflipMeta.NodeRefProto
                                .newBuilder()
                                .setName(n.getKey())
                                .build())
                        .collect(Collectors.toList()))
                .addAllOutputs(node.getOutputs()
                        .entrySet()
                        .stream()
                        .map(n -> HighflipMeta.NodeRefProto
                                .newBuilder()
                                .setName(n.getKey())
                                .build())
                        .collect(Collectors.toList()))
                .build();
        return proto;
    }

}
