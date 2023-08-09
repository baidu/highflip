package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.webank.ai.fate.client.form.dsl.Component;

import java.io.IOException;
import java.util.Map;

public class JsonMapComponentsDeserializer extends StdDeserializer<Map<String, Component>> {

    protected JsonMapComponentsDeserializer() {
        super(Map.class);
    }

    @Override
    public Map<String, Component> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
//        Map<String, Component> ret = new HashMap<>();
//        Map<String, Object> temp = jsonParser.getCodec().readValue(jsonParser, new TypeReference<Map<String, Object>>() {
//        });
//        for (String key : temp.keySet()) {
//            Object tempValue = temp.get(key);
//            if (tempValue != null) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                String decode = objectMapper.writeValueAsString(tempValue);
//                Component temp1 = objectMapper.readValue(decode, new TypeReference<Component>() {
//                });
//                ret.put(key, temp1);
//            }
//        }
//        return ret;
        return SerializerUtils.mapObjectDeserialize(jsonParser.getCodec().readTree(jsonParser).toString(), Component.class);
    }
}
