package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMapListStringDeserializer extends StdDeserializer<Map<String,List<String>>> {

    protected JsonMapListStringDeserializer() {
        super(Map.class);
    }

    @Override
    public Map<String, List<String>> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        Map<String, List<String>> ret = new HashMap<>();
        Map<String, Object> temp = jsonParser.getCodec().readValue(jsonParser, new TypeReference<Map<String, Object>>() {
        });
        for (String key : temp.keySet()) {
            Object tempValue = temp.get(key);
            if (tempValue != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String decode = objectMapper.writeValueAsString(tempValue);
                List<String> temp1 = objectMapper.readValue(decode, new TypeReference<List<String>>() {
                });
                ret.put(key, temp1);
            }
        }
        return ret;
    }
}
