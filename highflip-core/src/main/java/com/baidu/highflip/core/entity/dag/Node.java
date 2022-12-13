package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import com.baidu.highflip.core.utils.ProtoUtils;
import highflip.HighflipMeta;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Node extends AttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710002L;

    String name;

    String type;

    String description;

    Graph graph;

    Map<String, String> inputs;

    Map<String, String> outputs;

    @Data
    public class InputRef{

        Integer index;

        String name;

        String fromNode;

        String fromOutput;

        public Node getNode(){
            return graph.getNode(fromNode);
        }
    }

    @Data
    public class OutputRef{

        Integer index;

        String name;

        String toNode;

        String toInput;

        public Node getNode(){
            return graph.getNode(toInput);
        }
    }

    public static Node fromProto(HighflipMeta.NodeProto proto) {
        Node n = new Node();
        n.setName(proto.getName());
        n.setType(proto.getType());
        n.setDescription(proto.getDescription());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        return n;
    }

    public static HighflipMeta.NodeProto toProto(Node node) {
        HighflipMeta.NodeProto.Builder builder = HighflipMeta.NodeProto
                .newBuilder()
                .setName(node.getName())
                .setType(node.getType())
                .putAllAttributes(AttributeMap.toProto(node.getAttributes()))
                .addAllInputs(Objects.<Map<String, String>>requireNonNullElseGet(node.getInputs(), Map::of)
                        .entrySet()
                        .stream()
                        .map(n -> HighflipMeta.NodeRefProto
                                .newBuilder()
                                .setName(n.getKey())
                                .build())
                        .collect(Collectors.toList()))
                .addAllOutputs(Objects.<Map<String, String>>requireNonNullElseGet(node.getOutputs(), Map::of)
                        .entrySet()
                        .stream()
                        .map(n -> HighflipMeta.NodeRefProto
                                .newBuilder()
                                .setName(n.getKey())
                                .build())
                        .collect(Collectors.toList()));

        ProtoUtils.setOptional(builder, "Description", Optional.ofNullable(node.getDescription()));
        return builder.build();
    }

}
