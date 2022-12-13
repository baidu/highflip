package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import com.baidu.highflip.core.utils.ProtoUtils;
import highflip.HighflipMeta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Graph extends AttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710001L;

    String name;

    String description;

    Map<String, Node> nodes;

    Map<String, Party> parties;

    public static Graph fromProto(HighflipMeta.GraphProto proto) {
        Graph g = new Graph();
        g.setName(proto.getName());
        g.setDescription(proto.getDescription());
        g.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        g.setNodes(proto.getNodesList()
                .stream()
                .map(Node::fromProto)
                .peek(n -> n.setParent(g))
                .collect(Collectors.toMap(Node::getName, v -> v)));
        g.setParties(proto.getPartiesList()
                .stream()
                .map(Party::fromProto)
                .peek(n -> n.setParent(g))
                .collect(Collectors.toMap(Party::getName, v -> v)));
        return g;
    }

    public static HighflipMeta.GraphProto toProto(Graph graph) {
        HighflipMeta.GraphProto.Builder builder = HighflipMeta.GraphProto.newBuilder()
                .setName(graph.getName())
                .putAllAttributes(AttributeMap.toProto(graph.getAttributes()))
                .addAllNodes(graph.getNodes()
                        .values()
                        .stream()
                        .map(Node::toProto)
                        .collect(Collectors.toList()))
                .addAllParties(graph.getParties()
                        .values()
                        .stream()
                        .map(Party::toProto)
                        .collect(Collectors.toList()));

        ProtoUtils.setOptional(builder, "Description", Optional.ofNullable(graph.getDescription()));
        return builder.build();
    }

    public Node getNode(String name) {
        return getNodes().get(name);
    }

    public Party getParty(String name) {
        return getParties().get(name);
    }
}
