package com.baidu.highflip.core.entity.dag;

import com.baidu.highflip.core.entity.dag.codec.AttributeMap;
import com.baidu.highflip.core.entity.dag.common.AttributeObject;
import highflip.HighflipMeta;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;

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

    public Node getNodeByName(String name){
        throw new NotYetImplementedException();
    }

    public Party getPartyByName(String name){
        throw new NotYetImplementedException();
    }

    public static Graph fromProto(HighflipMeta.GraphProto proto){
        Graph g = new Graph();
        g.setName(proto.getName());
        g.setDescription(proto.getDescription());
        g.setAttributes(AttributeMap.fromProto(proto.getAttributesMap()));
        g.setNodes(proto.getNodesList()
                .stream()
                .map(Node::fromProto)
                .map(n -> {n.setParent(g); return n;})
                .collect(Collectors.toList()));
        g.setParties(proto.getPartiesList()
                .stream()
                .map(Party::fromProto)
                .map(n -> {n.setParent(g); return n;})
                .collect(Collectors.toList()));
        return g;
    }

    public static HighflipMeta.GraphProto toProto(Graph graph){
        throw new NotYetImplementedException();
    }
}
