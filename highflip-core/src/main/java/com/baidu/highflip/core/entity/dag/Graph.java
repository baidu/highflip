package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import highflip.HighflipMeta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Graph extends AttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710001L;

    String name;

    String description;

    List<Node> nodes;

    List<Party> parties;

    public static Graph fromProto(HighflipMeta.GraphProto proto) {
        Graph g = new Graph();
        g.setName(proto.getName());
        g.setDescription(proto.getDescription());
        g.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        g.setNodes(proto.getNodesList()
                .stream()
                .map(Node::fromProto)
                .map(n -> {
                    n.setParent(g);
                    return n;
                })
                .collect(Collectors.toList()));
        g.setParties(proto.getPartiesList()
                .stream()
                .map(Party::fromProto)
                .map(n -> {
                    n.setParent(g);
                    return n;
                })
                .collect(Collectors.toList()));
        return g;
    }

    public static HighflipMeta.GraphProto toProto(Graph graph) {
        HighflipMeta.GraphProto proto = HighflipMeta.GraphProto.newBuilder()
                .setName(graph.getName())
                .setDescription(graph.getDescription())
                .putAllAttributes(AttributeMap.toProto(graph.getAttributes()))
                .addAllNodes(graph.getNodes()
                        .stream()
                        .map(Node::toProto)
                        .collect(Collectors.toList()))
                .addAllParties(graph.getParties()
                        .stream()
                        .map(Party::toProto)
                        .collect(Collectors.toList()))
                .build();
        return proto;
    }

    public Node getNodeByName(String name) {
        return getNodes()
                .stream()
                .filter(n -> n.getName().compareToIgnoreCase(name) == 0)
                .findFirst()
                .orElseThrow();
    }

    public Party getPartyByName(String name) {
        return getParties()
                .stream()
                .filter(p -> p.getName().compareToIgnoreCase(name) == 0)
                .findFirst()
                .orElseThrow();
    }
}
