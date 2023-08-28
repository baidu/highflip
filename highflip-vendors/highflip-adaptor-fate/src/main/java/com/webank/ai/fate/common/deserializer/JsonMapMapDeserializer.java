package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapMapDeserializer extends StdDeserializer<Map<String, Map<String, String>>> {

    public JsonMapMapDeserializer() {
        super(Map.class);
    }

    @Override
    public Map<String, Map<String, String>> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        Map<String, Map<String, String>> ret = new HashMap<>();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> temp = jsonParser.getCodec().readValue(jsonParser, typeReference);
        for (String key : temp.keySet()) {
            Object tempValue = temp.get(key);
            if (tempValue != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String decode = objectMapper.writeValueAsString(tempValue);
                Map<String, Object> temp1 = objectMapper.readValue(decode, typeReference);
                Map<String, String> map = new HashMap<>();
                for (String key1 : temp.keySet()) {
                    if (temp1.get(key1) != null) {
                        map.put(key, temp1.get(key1).toString());
                    }
                }
                ret.put(key, map);
            }
        }
        return ret;
    }

}