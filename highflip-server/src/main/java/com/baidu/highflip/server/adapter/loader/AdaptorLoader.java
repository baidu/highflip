package com.baidu.highflip.server.adapter.loader;

import com.baidu.highflip.core.engine.HighFlipAdaptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import static com.baidu.highflip.core.common.AdaptorPropsList.HIGHFLIP_PROPERTIES_FILE;
import static com.baidu.highflip.core.common.AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_CLASS;

@Slf4j
@Component
public class AdaptorLoader {
    private URLClassLoader loader;

    private Properties props = new Properties();

    public void loadJar(URL url) throws IOException {

        loader = new URLClassLoader(new URL[]{url});

        try (InputStream stream = loader.getResourceAsStream(HIGHFLIP_PROPERTIES_FILE)) {
            props.load(stream);
        }
    }

    HighFlipAdaptor loadAdaptor() {
        HighFlipAdaptor adaptor = getInstance(PROPS_HIGHFLIP_ADAPTOR_CLASS);
        if (adaptor == null) {
            return null;
        }
        return adaptor;
    }


    public String getProperty(String prop) {
        String name = props.getProperty(prop, null);
        if (prop == null) {
            return null;
        }
        return name;
    }

    @SuppressWarnings( " unchecked " )
    public <T> T getInstance(String prop) {
        String name = props.getProperty(prop, null);
        if (prop == null) {
            return null;
        }

        try {
            Class<?> clazz = loader.loadClass(name);

            Constructor<?> cons = clazz.getConstructor();

            T instance = (T) cons.newInstance();
            return instance;
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }
}
