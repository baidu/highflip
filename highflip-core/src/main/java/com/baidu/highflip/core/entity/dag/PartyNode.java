package com.baidu.highflip.core.entity.dag;

import highflip.HighflipMeta;
import lombok.Data;
import org.hibernate.cfg.NotYetImplementedException;

import java.io.Serializable;
import java.util.Map;

@Data
public class PartyNode implements Serializable {

    private static final long serialVersionUID = 0x85710004L;

    String name;

    Map<String, Object> attributes;

    public static PartyNode fromProto(HighflipMeta.PartyProto.PartyNode proto){
        PartyNode n = new PartyNode();
        n.setName(proto.getName());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        return n;
    }

    public static HighflipMeta.PartyProto toProto(PartyNode node){
        throw new NotYetImplementedException();
    }
}
