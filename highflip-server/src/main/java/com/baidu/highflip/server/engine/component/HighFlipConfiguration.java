package com.baidu.highflip.server.engine.component;

import com.baidu.highflip.core.engine.Configuration;
import com.baidu.highflip.server.respository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Iterator;

@Component
public class HighFlipConfiguration implements Configuration {

    @Autowired
    ConfigurationRepository configs;

    protected com.baidu.highflip.server.entity.Configuration getEntry(String key){
        return configs.findById(key).orElse(null);
    }

    @Transactional
    protected void setEntry(String key, String value){
        com.baidu.highflip.server.entity.Configuration entity = getEntry(key);
        entity.setValue(value);
        configs.save(entity);
    }

    protected Iterator<com.baidu.highflip.server.entity.Configuration> listEntry(){
        return configs.findAll().stream().iterator();
    }

    public Iterator<String>  listKeys(){
        return configs.findAll()
                .stream().map(c -> c.getKey())
                .iterator();
    }

    @Override
    public String getString(String key, String defaultValue){
        com.baidu.highflip.server.entity.Configuration entry = getEntry(key);
        if (entry == null){
            return defaultValue;
        }
        return entry.getValue();
    }

    @Override
    public void setString(String key, String value){
        setEntry(key, value);
    }

    public Boolean getBoolean(String key, Boolean defaultValue){
        String value = getString(key, null);
        if (value == null){
            return defaultValue;
        }
        return Boolean.getBoolean(value);
    }

    public void setBoolean(String key, Boolean value){
        setString(key, value.toString());
    }
}
