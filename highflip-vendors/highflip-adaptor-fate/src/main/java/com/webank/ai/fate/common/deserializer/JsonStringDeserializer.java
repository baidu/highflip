package com.webank.ai.fate.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class JsonStringDeserializer extends StdDeserializer<String> {

    public JsonStringDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return node.toString().replace("\"", "");
    }

}
