package com.baidu.highflip.core.entity.runtime.basic;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Transient;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

public class Binding {

    @Transient
    ObjectMapper mapper;

    TreeMap<String, Object> values;

    public Binding(ObjectMapper mapper, TreeMap<String, Object> values) {
        this.mapper = mapper;
        this.values = values;
    }

    public Binding() {
        this.mapper = null;
        this.values = new TreeMap<>();
    }

    public Map<String, Object> getValues() {
        return this.values;
    }

    public void setObject(String key, Object value) {
        this.values.put(key, value);
    }

    public <T extends Class<?>> T getObject(String key, T defaultValue) throws IOException {
        Object value = this.values.getOrDefault(key, defaultValue);
        if (!(value instanceof BoxedObject)) {
            return defaultValue;

        }
        T unboxed = (T) ((BoxedObject) value).getObject(mapper, defaultValue.getClass());
        this.values.put(key, unboxed);
        return unboxed;
    }

    public void setBoolean(String key, Boolean value) {
        this.values.put(key, value);
    }

    public Boolean getBoolean(String key, Boolean def) {
        return (Boolean) values.getOrDefault(key, def);
    }

    public void setInteger(String key, Integer value) {
        this.values.put(key, value);
    }

    public Integer getInteger(String key, Integer def) {
        return (Integer) values.getOrDefault(key, def);
    }

    public void setFloat(String key, Float value) {
        this.values.put(key, value);
    }

    public Float getFloat(String key, Float def) {
        return (Float) values.getOrDefault(key, def);
    }

    public void setString(String key, String value) {
        this.values.put(key, value);
    }

    public String getString(String key, String def) {
        return (String) values.getOrDefault(key, def);
    }

    public static class BoxedObject {
        String value;

        public BoxedObject(String value) {
            this.value = value;
        }

        public <T> T getObject(ObjectMapper mapper, Class<T> clazz) throws IOException {
            byte[] bytes = Base64.getDecoder().decode(value);
            return mapper.readValue(bytes, clazz);
        }
    }
}
