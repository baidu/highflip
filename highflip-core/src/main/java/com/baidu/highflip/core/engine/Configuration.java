package com.baidu.highflip.core.engine;

public interface Configuration {

    String getString(String key, String defaultValue);

    void setString(String key, String value);
}
