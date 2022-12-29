package com.baidu.highflip.core.entity.dag.common;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.dag.Node;
import com.baidu.highflip.core.entity.dag.Party;
import com.baidu.highflip.core.entity.dag.PartyNode;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MappedNode {

    public static final int PARTY_NODE_INDEX = 0;
    public static final int PARTY_INDEX = 1;
    public static final int NODE_INDEX = 2;
    public static final int GRAPH_INDEX = 3;
    List<NamedAttributeObject> inherit;

    public static MappedNode of(Node node, String partyName) {
        Graph graph = node.getGraph();
        Party party = graph.getParty(partyName);
        PartyNode partyNode = party.getPartyNode(node.getName());

        MappedNode mapped = new MappedNode();
        mapped.inherit = List.of(
                partyNode,
                party,
                node,
                graph);
        return mapped;
    }

    public Node getNode() {
        return (Node) inherit.get(NODE_INDEX);
    }

    public Graph getGraph() {
        return (Graph) inherit.get(GRAPH_INDEX);
    }

    public Party getParty() {
        return (Party) inherit.get(PARTY_INDEX);
    }

    public PartyNode getPartyNode() {
        return (PartyNode) inherit.get(PARTY_NODE_INDEX);
    }

    public String getName() {
        return getNode().getName();
    }

    public Object getForward(String key, Object defaultValue) {
        for (NamedAttributeObject obj : inherit) {
            if (obj != null) {
                Object attr = obj.getAttribute(key, null);
                if (attr != null) {
                    return attr;
                }
            }
        }
        return defaultValue;
    }

    public Set<String> getForwardKeys() {
        return inherit.stream()
                .filter(Objects::nonNull)
                .flatMap(o -> o.getAttributes().keySet().stream())
                .collect(Collectors.toSet());
    }
}
