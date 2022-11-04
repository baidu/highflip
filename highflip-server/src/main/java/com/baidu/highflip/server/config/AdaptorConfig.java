package com.baidu.highflip.server.config;

import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.server.adapter.DefaultAdaptor;
import com.baidu.highflip.server.adapter.impl.ConfigurablePlatformAdaptor;
import com.baidu.highflip.server.adapter.loader.AdaptorLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

@Slf4j
@Configuration
public class AdaptorConfig {

    @Value("${highflip.server.adaptor.path:#{null}}")
    URL adaptorPath;

    @Autowired
    InstanceRegister register;

    AdaptorLoader loader = null;

    @PostConstruct
    void initialize() {

        initializeLoader();

        initialDefaultAdaptor();

        initialAdaptor();
    }

    @PreDestroy
    void unintialize() {
        if (loader != null) {
            loader.unloadAdaptor(register);
        }
    }

    void initializeLoader() {
        if (adaptorPath == null) {
            log.error("Skipped loading adaptor for vacancy of jar path setting.");
            return;
        }

        try {
            AdaptorLoader loader = new AdaptorLoader();

            loader.loadJar(adaptorPath);
            log.info("Loaded adaptor jar from path: {}", adaptorPath);

            this.loader = loader;
        } catch (IOException e) {
            log.error("Failed to load adaptor from path: {}", adaptorPath, e);
            this.loader = null;
        }

    }

    void initialAdaptor() {
        if (loader == null) {
            return;
        }

        loader.loadAdaptor(register);
    }

    void initialDefaultAdaptor() {
        DefaultAdaptor adaptor = new DefaultAdaptor();
        adaptor.setup(register);
    }
}
