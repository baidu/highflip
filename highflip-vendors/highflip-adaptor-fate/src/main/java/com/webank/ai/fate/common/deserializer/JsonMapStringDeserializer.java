package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapStringDeserializer extends StdDeserializer<Map<String,String>> {

    public JsonMapStringDeserializer(){
        super(Map.class);
    }

    @Override
    public Map<String,String> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        Map<String, String> map = new HashMap<>();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> temp = jsonParser.getCodec().readValue(jsonParser, typeReference);
        for (String key : temp.keySet()) {
            if (temp.get(key) != null) {
                map.put(key, temp.get(key).toString());
            }
        }
        return map;
//        return SerializerUtils.mapObjectDeserialize(jsonParser.getCodec().readTree(jsonParser).toString(), String.class);
    }
}
