package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonListDeserializer extends StdDeserializer<List<String>> {

    public JsonListDeserializer() {
        super(List.class);
    }

    @Override
    public List<String> deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        List<String> result = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode element : node) {
                result.add(element.asText());
            }
        } else if (node.isObject()) {
            result.add(node.asText());
        } else {
            //maybe nothing?
        }
        return result;
    }
}