package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.NamedAttributeObject;
import highflip.HighflipMeta;
import lombok.Data;

import java.io.Serializable;

@Data
public class PartyNode extends NamedAttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710004L;

    public static PartyNode of(String name) {
        PartyNode node = new PartyNode();
        node.setName(name);
        return node;
    }

    public static PartyNode fromProto(HighflipMeta.PartyProto.PartyNode proto) {
        PartyNode n = new PartyNode();
        n.setName(proto.getName());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        return n;
    }

    public static HighflipMeta.PartyProto.PartyNode toProto(PartyNode node) {
        HighflipMeta.PartyProto.PartyNode proto = HighflipMeta.PartyProto.PartyNode
                .newBuilder()
                .setName(node.getName())
                .putAllAttributes(AttributeMap.toProto(node.getAttributes()))
                .build();
        return proto;
    }
}
