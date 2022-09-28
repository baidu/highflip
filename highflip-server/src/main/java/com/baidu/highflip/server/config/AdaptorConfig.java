package com.baidu.highflip.server.config;

import com.baidu.highflip.core.engine.HighFlipAdaptor;
import com.baidu.highflip.server.engine.loader.AdaptorLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URL;

import static com.baidu.highflip.core.common.AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_CLASS;

@Slf4j
@Configuration
public class AdaptorConfig {

    @Value("${highflip.server.adaptor.path:#{null}}")
    URL adaptorPath;

    AdaptorLoader loader = null;

    HighFlipAdaptor adaptor = null;

    @PostConstruct
    void initialize() throws IOException {

        initializeLoader();

        loadAdaptor();
    }

    @PreDestroy
    void unintialize(){
        if (adaptor != null) {
            adaptor.clean(null);
        }
    }

    void initializeLoader() throws IOException {
        if (adaptorPath != null) {
            AdaptorLoader loader = new AdaptorLoader();
            loader.loadJar(adaptorPath);
            log.info("Loaded adaptor jar from path: {}", adaptorPath);

            this.loader = loader;
        }
    }

    void loadAdaptor() {
        if (loader == null) {
            return;
        }

        this.adaptor = loader.getInstance(PROPS_HIGHFLIP_ADAPTOR_CLASS);
        if (this.adaptor == null) {
            return;
        }

        adaptor.setup(null);
    }

    public void reload(){

        unintialize();

        loadAdaptor();
    }
}
