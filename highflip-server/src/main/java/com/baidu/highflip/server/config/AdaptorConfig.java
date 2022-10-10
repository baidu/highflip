package com.baidu.highflip.server.config;

import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.server.adapter.loader.AdaptorLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Configuration
public class AdaptorConfig {

    @Value("${highflip.server.adaptor.path:#{null}}")
    URL adaptorPath;

    @Autowired
    InstanceRegister register;

    AdaptorLoader loader = null;

    @PostConstruct
    void initialize() throws IOException {

        initializeLoader();

        if (loader != null) {
            loader.loadAdaptor(register);
        }
    }

    @PreDestroy
    void unintialize() {
        if (loader != null) {
            loader.unloadAdaptor(register);
        }
    }

    void initializeLoader() throws IOException {
        if (adaptorPath != null) {
            AdaptorLoader loader = new AdaptorLoader();
            this.loader = loader;

            loader.loadJar(adaptorPath);
            log.info("Loaded adaptor jar from path: {}", adaptorPath);
        }
    }
}
