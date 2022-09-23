package com.baidu.highflip.core.entity.dag;

import highflip.HighflipMeta;
import lombok.Data;
import org.hibernate.cfg.NotYetImplementedException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Graph implements Serializable {

    private static final long serialVersionUID = 0x85710001L;

    String name;

    String description;

    Map<String, Object> attributes;

    List<Node> nodes;

    List<Party> parties;

    public static Graph fromProto(HighflipMeta.GraphProto proto){
        Graph g = new Graph();
        g.setName(proto.getName());
        g.setDescription(proto.getDescription());
        g.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        g.setNodes(proto.getNodesList()
                .stream()
                .map(Node::fromProto)
                .collect(Collectors.toList()));
        g.setParties(proto.getPartiesList()
                .stream()
                .map(Party::fromProto)
                .collect(Collectors.toList()));
        return g;
    }

    public static HighflipMeta.GraphProto toProto(Graph graph){
        throw new NotYetImplementedException();
    }
}
