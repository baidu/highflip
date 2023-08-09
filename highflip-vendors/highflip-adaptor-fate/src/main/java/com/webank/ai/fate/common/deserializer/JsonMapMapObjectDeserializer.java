package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapMapObjectDeserializer extends StdDeserializer<Map<String, Map<String, Object>>> {

    public JsonMapMapObjectDeserializer() {
        super(Map.class);
    }

    @Override
    public Map<String, Map<String, Object>> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        Map<String, Map<String, Object>> ret = new HashMap<>();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> temp = jsonParser.getCodec().readValue(jsonParser, typeReference);
        for (String key : temp.keySet()) {
            Object tempValue = temp.get(key);
            if (tempValue != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String decode = objectMapper.writeValueAsString(tempValue);
                Map<String, Object> temp1 = objectMapper.readValue(decode, typeReference);
                ret.put(key, temp1);
            }
        }
        return ret;
    }

}
