package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import highflip.HighflipMeta;
import lombok.Data;
import org.hibernate.cfg.NotYetImplementedException;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Map;

@Data
public class PartyNode extends AttributeObject implements Serializable {

    private static final long serialVersionUID = 0x85710004L;

    @Transient
    Party parent;

    String name;

    Map<String, Object> attributes;

    public static PartyNode fromProto(HighflipMeta.PartyProto.PartyNode proto) {
        PartyNode n = new PartyNode();
        n.setName(proto.getName());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        return n;
    }

    public static HighflipMeta.PartyProto toProto(PartyNode node) {
        throw new NotYetImplementedException();
    }
}
