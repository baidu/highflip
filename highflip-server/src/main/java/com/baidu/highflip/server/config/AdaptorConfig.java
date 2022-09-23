package com.baidu.highflip.server.config;

import com.baidu.highflip.adaptor.dumb.DumbJobAdaptor;
import com.baidu.highflip.adaptor.dumb.DumbTaskAdaptor;
import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.adaptor.TaskAdaptor;
import com.baidu.highflip.server.engine.loader.AdaptorLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Configuration
public class AdaptorConfig {

    @Value("${highflip.server.adaptor.path:#{null}}")
    URL adaptorPath;


    AdaptorLoader loader = null;

    @PostConstruct
    void initialize() throws IOException {
        if (adaptorPath != null) {
            AdaptorLoader loader = new AdaptorLoader();
            loader.loadJar(adaptorPath);
            log.info("Loaded adaptor jar from path: {}", adaptorPath);

            this.loader = loader;
        }
    }

    @Bean
    JobAdaptor getJobAdaptor() {
        if (loader != null) {
            return loader.getInstance(AdaptorLoader.PROPS_HIGHFLIP_ADAPTOR_JOB_CLASS);
        }
        return new DumbJobAdaptor();
    }

    @Bean
    TaskAdaptor getTaskAdaptor() {
        if (loader != null) {
            return loader.getInstance(AdaptorLoader.PROPS_HIGHFLIP_ADAPTOR_TASK_CLASS);
        }
        return new DumbTaskAdaptor();
    }
}
