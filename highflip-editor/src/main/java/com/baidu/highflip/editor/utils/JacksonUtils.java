package com.baidu.highflip.editor.utils;


import java.io.IOException;

import com.baidu.highflip.editor.model.HighFlipEditorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * utils for serialization and deserialization
 *
 * @author songtingyu
 */
public class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                         false);
        mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, false);
    }

    private JacksonUtils() {

    }

    public static <T> String toJson(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new HighFlipEditorException(400, "failed to serialize obj.", e);
        }
    }

    public static <T> T fromJson(String json,  Class<T> valueType) {
        if (json == null || json.isEmpty()) {
            throw new HighFlipEditorException(400, "json is empty!");
        }
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException e) {
            throw new HighFlipEditorException(400, e.getMessage(), e);
        }
    }
}

