package com.baidu.highflip.core.entity.dag.common;

import com.baidu.highflip.core.entity.dag.Graph;
import lombok.Data;

import java.util.Optional;

@Data
public class Reference {

    Graph graph = null;

    Proto proto;

    String name;

    Type type;

    String key;

    static Optional<Proto> toProto(String value) {
        try {
            return Optional.of(Proto.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    static Optional<Type> toType(String value) {
        try {
            return Optional.of(Type.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public static Reference valueOf(Graph graph, String expr) {
        Reference ref = new Reference();
        ref.setExpression(expr);
        ref.setGraph(graph);
        return ref;
    }

    public static Reference valueOf(String expr) {
        Reference ref = new Reference();
        ref.setExpression(expr);
        return ref;
    }

    public void setExpression(String expr) {
        String[] items = expr.split("\\.", 4);

        int index = 0;

        Optional<Proto> proto = toProto(items[index]);
        if (proto.isPresent()) {
            this.proto = proto.get();
            index += 1;
        } else {
            this.proto = Proto.PARTY_NODE;
        }

        name = items[index];
        index += 1;


        Optional<Type> type = toType(items[index]);
        if (type.isPresent()) {
            this.type = type.get();
            index += 1;
        } else {
            this.type = Type.OUTPUT;
        }

        name = items[index];
        index += 1;
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s.%s",
                getProto().toString().toLowerCase(),
                getName(),
                getType().toString().toLowerCase(),
                getKey());
    }

    NamedAttributeObject getObject() {
        switch (getProto()) {
            case GRAPH:
                return getGraph();
            case NODE:
                return getGraph().getNode(getName());
            case PARTY:
                return getGraph().getParty(getName());
            case PARTY_NODE:
            default:
                return getGraph().getParty(getName());
        }
    }

    Object getAttribute() {
        return getObject().getAttributes().get(getKey());
    }

    Object getOutput() {
        switch (getProto()) {
            default:
            case NODE:
                return getGraph().getNode(getName()).getOutputs().get(getKey());
        }
    }

    Object getInput() {
        switch (getProto()) {
            default:
            case NODE:
                return getGraph().getNode(getName()).getInputs().get(getKey());
        }
    }

    public Object eval() {
        switch (getType()) {
            case ATTRIBUTE:
                return getAttribute();
            case INPUT:
                return getInput();
            default:
            case OUTPUT:
                return getOutput();
        }
    }

    public enum Proto {
        GRAPH,
        NODE,
        PARTY,
        PARTY_NODE,
    }

    public enum Type {
        ATTRIBUTE,
        INPUT,
        OUTPUT,
    }
}
