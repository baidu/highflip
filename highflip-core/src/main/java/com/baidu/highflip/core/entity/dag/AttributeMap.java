package com.baidu.highflip.core.entity.dag;

import highflip.HighflipMeta;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.Map;
import java.util.stream.Collectors;

public class AttributeMap {

    public static Map<String, Object> fromProto(Map<String, HighflipMeta.TypedValueProto> proto){
        Map<String, Object> attributes = proto.entrySet()
                .stream()
                .map( attr -> {
                    String key = attr.getKey();
                    Object value = TypeValue.fromProto(attr.getValue());
                    return Map.entry(key, value);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return attributes;
    }

    public static Map<String, HighflipMeta.TypedValueProto> toProto(Map<String, Object> map){
        throw new NotYetImplementedException();
    }
}
