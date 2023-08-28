package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.NamedAttributeObject;
import com.baidu.highflip.core.utils.ProtoUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import highflip.HighflipMeta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Graph extends NamedAttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710001L;

    String description;

    Map<String, Node> nodes;

    Map<String, Party> parties;

    Set<Node> inputs;

    Set<Node> outputs;

    public static Graph fromProto(HighflipMeta.GraphProto proto) {
        Graph g = new Graph();
        g.setName(proto.getName());
        g.setDescription(proto.getDescription());
        g.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        g.setNodes(proto.getNodesList()
                .stream()
                .map(Node::fromProto)
                .peek(n -> n.setParent(g))
                .peek(n -> n.setGraph(g))
                .collect(Collectors.toMap(Node::getName, v -> v)));
        g.setParties(proto.getPartiesList()
                .stream()
                .map(Party::fromProto)
                .peek(n -> n.setParent(g))
                .collect(Collectors.toMap(Party::getName, v -> v)));

        g.setNodeCategory();
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

        ProtoUtils.setOptional(builder, "Description", ProtoUtils.ofString(graph.getDescription()));
        return builder.build();
    }

    /**
     * @param name
     * @return
     */
    public Node getNode(String name) {
        return getNodes().get(name);
    }

    /**
     * @return
     */
    public Iterable<String> listNode() {
        return getNodes().keySet();
    }

    /**
     * @param name
     * @return
     */
    public Party getParty(String name) {
        return getParties().get(name);
    }

    public Iterable<String> listParties() {
        return getParties().keySet();
    }

    @JsonIgnore
    protected void setNodeCategory() {
        calcMiddleNodes();
        calcOutputNodes();
        calcInputNodes();
    }

    protected void calcInputNodes() {
        getNodes()
                .values()
                .stream()
                .filter(n -> n.getInputNodes().isEmpty())
                .forEach(n -> n.setCategory(Node.Category.INPUT_NODE));
    }

    protected void calcMiddleNodes() {
        getNodes()
                .values()
                .stream()
                .flatMap(n -> n.getInputNodes().stream())
                .filter(Objects::nonNull)
                .distinct()
                .forEach(n -> n.setCategory(Node.Category.MIDDLE_NODE));
    }

    protected void calcOutputNodes() {
        getNodes()
                .values()
                .stream()
                .filter(n -> n.getCategory() != Node.Category.INPUT_NODE
                        && n.getCategory() != Node.Category.MIDDLE_NODE)
                .distinct()
                .forEach(n -> n.setCategory(Node.Category.OUTPUT_NODE));
    }
}
