package com.baidu.highflip.core.entity.dag.common;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class NamedAttributeObject implements Comparable<NamedAttributeObject> {

    @Transient
    List<NamedAttributeObject> parents = List.of();

    String name;

    Map<String, Object> attributes = new TreeMap<String, Object>();

    @Override
    public int compareTo(NamedAttributeObject attributeObject) {
        return this.getName().compareTo(attributeObject.getName());
    }

    @Override
    public boolean equals(Object other) {
        return this.getName().equals(other);
    }

    public List<NamedAttributeObject> getParents() {
        return parents;
    }

    public void setParents(List<NamedAttributeObject> parents) {
        this.parents = parents;
    }

    public void setParent(NamedAttributeObject parent) {
        this.parents = List.of(parent);
    }

    public Object getAttribute(String name, Object defaultValue) {
        return attributes.getOrDefault(name, defaultValue);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attrs) {
        attributes.putAll(attrs);
    }

    public void setAttribute(String name, Object attr) {
        attributes.put(name, attr);
    }

    public Object getForward(String name, Object defaultValue) {

        Object current = getAttribute(name, null);
        if (current != null) {
            return current;
        }

        for (NamedAttributeObject parent : getParents()) {
            return parent.getForward(name, defaultValue);
        }
        return defaultValue;
    }


}
