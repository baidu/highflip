package com.baidu.highflip.core.entity.dag;

import highflip.HighflipMeta;
import lombok.Data;
import org.hibernate.cfg.NotYetImplementedException;

import java.io.Serializable;
import java.util.Map;

@Data
public class Node implements Serializable {

    private static final long serialVersionUID = 0x85710002L;

    String name;

    String type;

    String description;

    Map<String, Object> attributes;

    Map<String, String> inputs;

    Map<String, String> outputs;

    public static Node fromProto(HighflipMeta.NodeProto proto){
        Node n = new Node();
        n.setName(proto.getName());
        n.setDescription(proto.getDescription());
        n.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        return n;
    }

    public static HighflipMeta.NodeProto toProto(Node node){
        throw new NotYetImplementedException();
    }

}
