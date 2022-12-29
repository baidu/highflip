package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.MappedNode;
import com.baidu.highflip.core.entity.dag.common.NamedAttributeObject;
import com.baidu.highflip.core.entity.dag.common.NodeInputRef;
import com.baidu.highflip.core.entity.dag.common.NodeOutputRef;
import com.baidu.highflip.core.utils.ProtoUtils;
import highflip.HighflipMeta;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Node extends NamedAttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710002L;

    String type;

    String description;

    @Transient
    Graph graph;

    Category category;

    Map<String, NodeInputRef> inputs = Map.of();

    Map<String, NodeOutputRef> outputs = Map.of();

    public static Node fromProto(HighflipMeta.NodeProto proto) {
        Node n = new Node();
        n.setName(proto.getName());
        n.setType(proto.getType());
        n.setDescription(proto.getDescription());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        n.setInputs(proto.getInputsList()
                .stream()
                .map(NodeInputRef::fromProto)
                .collect(Collectors.toMap(NodeInputRef::getName, v -> v)));
        n.setOutputs(proto.getOutputsList()
                .stream()
                .map(NodeOutputRef::fromProto)
                .collect(Collectors.toMap(NodeOutputRef::getName, v -> v)));
        return n;
    }

    public static HighflipMeta.NodeProto toProto(Node node) {
        HighflipMeta.NodeProto.Builder builder = HighflipMeta.NodeProto
                .newBuilder()
                .setName(node.getName())
                .setType(node.getType())
                .putAllAttributes(AttributeMap.toProto(node.getAttributes()))
                .addAllInputs(node.getInputs()
                        .values()
                        .stream()
                        .map(NodeInputRef::toProto)
                        .collect(Collectors.toList()))
                .addAllOutputs(node.getOutputs()
                        .values()
                        .stream()
                        .map(NodeOutputRef::toProto)
                        .collect(Collectors.toList()));

        ProtoUtils.setOptional(builder, "Description", ProtoUtils.ofString(node.getDescription()));
        return builder.build();
    }

    @Override
    public boolean equals(Object others) {
        return super.equals(others);
    }

    @Override
    public String toString() {
        return String.format("Node(name=%s, type=%s, category=%s)",
                getName(),
                getType(),
                getCategory());
    }

    /**
     * @param party
     * @return
     */
    public PartyNode getPartyNode(String party) {
        return getGraph()
                .getParty(party)
                .getPartyNode(this.getName());
    }

    public MappedNode getMappedNode(String party) {
        return MappedNode.of(this, party);
    }

    public NodeInputRef getInputRef(String name) {
        return getInputs().get(name);
    }

    public Node getInputNode(String name) {
        return getGraph().getNode(
                getInputs().get(name).getFromNode());
    }

    public List<Node> getInputNodes() {
        return getInputs()
                .values()
                .stream()
                .map(n -> getGraph().getNode(n.getFromNode()))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    public NodeOutputRef getOutputRef(String name) {
        return getOutputs().get(name);
    }

    public enum Category {
        INPUT_NODE,
        MIDDLE_NODE,
        OUTPUT_NODE,
    }
}
