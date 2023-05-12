package com.baidu.highflip.core.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.lang.Nullable;

public class SerializerUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private SerializerUtils() {
    }

    public static <T> Map<String, T> mapObjectDeserialize(String object,
                                                          Class<T> tClass)
            throws JsonProcessingException {
        if (object == null || object.isBlank()) {
            return null;
        }
        Map<String, T> ret = new HashMap<>();
        Map<String, Object> map = objectMapper.readValue(object,
                                                         new TypeReference<Map<String, Object>>() {
                                                         });
        for (String tempKey : map.keySet()) {
            Object tempValue = map.get(tempKey);
            if (tempValue != null) {
                String decodeValue = objectMapper.writeValueAsString(tempValue);
                if (tClass.isAssignableFrom(String.class)) {
                    //noinspection unchecked
                    ret.put(tempKey, (T) decodeValue);
                } else {
                    T result = objectMapper.readValue(decodeValue, tClass);
                    ret.put(tempKey, result);
                }
            }
        }
        return ret;
    }

    public static String toJsonString(Object object)
            throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T deserialize(String json, Class<T> tClass)
            throws JsonProcessingException {
        return objectMapper.readValue(json, tClass);
    }

    @Nullable
    public static <T> T deserializeType(String json,
                                        TypeReference<T> typeReference)
            throws JsonProcessingException {
        if (json == null || json.isBlank()) {
            return null;
        }
        return objectMapper.readValue(json, typeReference);
    }
}
