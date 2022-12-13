package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import com.baidu.highflip.core.utils.ProtoUtils;
import highflip.HighflipMeta;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Party extends AttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710003L;

    String name;

    String description;

    String role;

    Map<String, PartyNode> nodes;

    public static Party fromProto(HighflipMeta.PartyProto proto) {
        Party p = new Party();
        p.setName(proto.getName());
        p.setRole(proto.getRole().toString());
        p.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        p.setNodes(proto.getNodesList()
                .stream()
                .map(PartyNode::fromProto)
                .peek(n -> n.setParent(p))
                .collect(Collectors.toMap(PartyNode::getName, v -> v)));
        return p;
    }

    public static HighflipMeta.PartyProto toProto(Party party) {

        HighflipMeta.PartyProto.Builder builder = HighflipMeta.PartyProto.newBuilder()
                .setName(party.getName())
                .putAllAttributes(AttributeMap.toProto(party.getAttributes()))
                .setRole(HighflipMeta.PartyRole.valueOf(party.getRole()))
                .addAllNodes(party.getNodes()
                        .values()
                        .stream()
                        .map(PartyNode::toProto)
                        .collect(Collectors.toList()));

        ProtoUtils.setOptional(builder, "Description", Optional.ofNullable(party.getDescription()));
        return builder.build();
    }

    @Override
    public void setParent(AttributeObject parent) {
        Graph graph = (Graph) parent;
        for (PartyNode pn : nodes.values()) {
            Node node = graph.getNode(pn.getName());
            pn.setParents(List.of(this, node));
        }
    }

    public PartyNode getPartyNode(String name) {
        return getNodes().get(name);
    }
}
