package com.baidu.highflip.server.engine.component;

import com.baidu.highflip.core.engine.Configuration;
import com.baidu.highflip.core.entity.runtime.Config;
import com.baidu.highflip.server.respository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Iterator;


@Component
public class HighFlipConfiguration implements Configuration {

    @Autowired
    ConfigurationRepository configs;

    public Config getEntry(String key) {
        return configs.findById(key)
                .orElse(null);
    }

    @Transactional
    public void setEntry(String key, String value) {
        Config entity = getEntry(key);
        if (entity != null) {
            entity.setValue(value);
        } else {
            entity = new Config();
            entity.setKey(key);
            entity.setValue(value);
        }
        configs.save(entity);
    }

    protected Iterator<Config> listEntry() {
        return configs.findAll()
                .stream()
                .iterator();
    }

    /**
     * @return
     */
    public Iterable<String> listKeys() {
        return () -> configs.findAll()
                .stream()
                .map(c -> c.getKey())
                .iterator();
    }

    /**
     * @param key
     */
    public void delete(String key) {
        configs.deleteById(key);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    @Override
    public String getString(String key, String defaultValue) {
        Config entry = getEntry(key);
        if (entry == null) {
            return defaultValue;
        }
        return entry.getValue();
    }

    @Override
    public void setString(String key, String value) {
        setEntry(key, value);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = getString(key, null);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    public void setBoolean(String key, Boolean value) {
        setString(key, value.toString());
    }
}
