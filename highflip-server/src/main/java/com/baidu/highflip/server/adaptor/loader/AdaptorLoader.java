package com.baidu.highflip.server.adaptor.loader;

import com.baidu.highflip.core.common.AdaptorPropsList;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.core.engine.InstanceRegister;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.loader.LaunchedURLClassLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

@Slf4j
@Component
public class AdaptorLoader {
    private LaunchedURLClassLoader loader;

    private Properties props = new Properties();

    public void loadJar(URL url) throws IOException {

        loader = (LaunchedURLClassLoader) Thread.currentThread()
                                                .getContextClassLoader();
        try {
            Method addURL =
                    LaunchedURLClassLoader.class.getSuperclass()
                                                .getDeclaredMethod("addURL",
                                                                   new Class[] {URL.class});
            addURL.setAccessible(true);
            addURL.invoke(loader, new Object[] {url});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (InputStream stream = loader
                .getResourceAsStream(AdaptorPropsList.HIGHFLIP_PROPERTIES_FILE)) {

            if (stream != null) {
                props.load(stream);
            } else {
                log.info("Missed {} property file.", AdaptorPropsList.HIGHFLIP_PROPERTIES_FILE);
            }
        }
    }

    public Properties getProperties() {
        return props;
    }


    public String getProperty(String prop, String defaultValue) {
        String name = props.getProperty(prop, null);
        if (prop == null) {
            return defaultValue;
        }
        return name;
    }

    @SuppressWarnings(" unchecked ")
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

    public void loadAdaptor(InstanceRegister register) {

        HighFlipAdaptor adaptor = this.getInstance(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_CLASS);
        if (adaptor == null) {
            log.error("Failed to create highflip adaptor instance.");
            return;
        }

        String adaptorName = this.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_NAME,
                AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_NAME_DEFAULT);

        String adaptorVersion = this.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_VERSION,
                AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_VERSION_DEFAULT);


        log.info("Setup highflip adaptor: {}", adaptor.getClass());
        adaptor.setup(register);

        register.register(InstanceNameList.HIGHFLIP_ADAPTOR, adaptor);
        log.info("Loaded highfilip adaptor: {}:{}", adaptorName, adaptorVersion);
    }


    public void unloadAdaptor(InstanceRegister register) {
        HighFlipAdaptor adaptor = (HighFlipAdaptor) register.revoke(InstanceNameList.HIGHFLIP_ADAPTOR);

        if (adaptor == null) {
            return;
        }

        log.info("Clean up highflip adaptor");
        adaptor.clean(register);
    }
}
