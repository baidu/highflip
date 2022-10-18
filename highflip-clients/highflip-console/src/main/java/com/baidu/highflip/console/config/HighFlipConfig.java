package com.baidu.highflip.console.config;

import com.baidu.highflip.client.HighFlipClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class HighFlipConfig {

    @Value("${highflip.server.url:#{null}}")
    String highflipUrl;

    @Bean
    HighFlipClient getClient() {
        HighFlipClient client = new HighFlipClient();

        if (highflipUrl != null && !highflipUrl.isEmpty()) {
            log.info("highflip.url={}", highflipUrl);
            client.connect(highflipUrl);
        }

        return client;
    }
}
