package com.baidu.highflip.server.engine.loader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

@Slf4j
public class AdaptorLoader {
    public static final String PROPS_HIGHFLIP_ADAPTOR_NAME = "highflip.adaptor.name";

    public static final String PROPS_HIGHFLIP_ADAPTOR_VERSION = "highflip.adaptor.version";
    public static final String PROPS_HIGHFLIP_ADAPTOR_JOB_CLASS = "highflip.adaptor.job.class";
    public static final String PROPS_HIGHFLIP_ADAPTOR_TASK_CLASS = "highflip.adaptor.task.class";
    private static final String HIGHFLIP_PROPERTIES = "highflip.properties";
    private URLClassLoader loader;

    private Properties props = new Properties();

    public void loadJar(URL url) throws IOException {

        loader = new URLClassLoader(new URL[]{url});

        try (InputStream stream = loader.getResourceAsStream(HIGHFLIP_PROPERTIES)) {
            props.load(stream);
        }
    }

    public String getProperty(String prop) {
        String name = props.getProperty(prop, null);
        if (prop == null) {
            return null;
        }
        return name;
    }

    public <T> T getInstance(String prop) {
        String name = props.getProperty(prop, null);
        if (prop == null) {
            return null;
        }

        try {
            Class<?> clazz = loader.loadClass(name);

            Constructor<?> cons = clazz.getConstructor();

            T inst = (T) cons.newInstance();
            return inst;
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}