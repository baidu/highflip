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
        if (highflipUrl == null || highflipUrl.isEmpty()) {
            return new HighFlipClient();
        } else {
            log.info("highflip.url={}", highflipUrl);
            return HighFlipClient.connect(highflipUrl);
        }
    }
}
