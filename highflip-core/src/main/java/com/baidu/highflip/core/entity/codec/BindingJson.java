package com.baidu.highflip.core.entity.codec;

import com.baidu.highflip.core.entity.runtime.basic.Binding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

@JsonComponent
public class BindingJson {

    public static final String OBJECT_PREFIX = "base64,";
    @Autowired
    static ObjectMapper mapper;

    public static class Serializer extends JsonSerializer<Binding> {

        @Override
        public void serialize(Binding binding, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();

            for (Map.Entry<String, Object> entry : binding.getValues().entrySet()) {
                gen.writeFieldName(entry.getKey());

                Class<?> clazz = entry.getValue().getClass();
                Object value = entry.getValue();
                if (Integer.class.equals(clazz)
                        || Boolean.class.equals(clazz)
                        || Short.class.equals(clazz)
                        || Long.class.equals(clazz)
                        || Float.class.equals(clazz)
                        || Double.class.equals(clazz)
                        || String.class.equals(clazz)) {
                    gen.writeObject(value);
                } else {
                    byte[] bytes = mapper.writeValueAsBytes(value);
                    String base64 = Base64.getEncoder().encodeToString(bytes);
                    gen.writeString(OBJECT_PREFIX + base64);
                }
            }
            gen.writeEndObject();
        }
    }

    public static class Deserializer extends JsonDeserializer<Binding> {

        @Override
        public Binding deserialize(JsonParser parser, DeserializationContext context) throws IOException {

            ObjectCodec codec = parser.getCodec();

            TreeMap<String, Object> result = new TreeMap<>();
            codec.<JsonNode>readTree(parser)
                    .fields()
                    .forEachRemaining(entry -> {
                        String name = entry.getKey();
                        JsonNode value = entry.getValue();

                        if (value.isBoolean()) {
                            result.put(name, value.booleanValue());
                        } else if (value.isInt()) {
                            result.put(name, value.intValue());
                        } else if (value.isShort()) {
                            result.put(name, value.shortValue());
                        } else if (value.isLong()) {
                            result.put(name, value.longValue());
                        } else if (value.isFloat()) {
                            result.put(name, value.floatValue());
                        } else if (value.isDouble()) {
                            result.put(name, value.doubleValue());
                        } else {
                            String text = value.textValue();
                            if (text.startsWith(OBJECT_PREFIX)) {
                                result.put(name, new Binding.BoxedObject(text));
                            } else {
                                result.put(name, text);
                            }
                        }
                    });
            return new Binding((ObjectMapper) codec, result);
        }
    }
}
