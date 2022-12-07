package com.baidu.highflip.core.entity.dag.codec;

import highflip.HighflipMeta;

import java.util.Map;
import java.util.stream.Collectors;

public class AttributeMap {

    public static Map<String, Object> fromProto(Map<String, HighflipMeta.TypedValueProto> proto) {
        Map<String, Object> attributes = proto.entrySet()
                .stream()
                .map(attr -> Map.entry(attr.getKey(),
                        TypeValue.fromProto(attr.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return attributes;
    }

    public static Map<String, HighflipMeta.TypedValueProto> toProto(Map<String, Object> map) {
        Map<String, HighflipMeta.TypedValueProto> proto = map.entrySet()
                .stream()
                .map(n -> Map.entry(n.getKey(),
                        TypeValue.toProto(n.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return proto;
    }
}
