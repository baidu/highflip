package com.baidu.highflip.console.config;

import com.baidu.highflip.client.HighFlipClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HighFlipConfig {

    @Bean
    HighFlipClient getClient() {
        return new HighFlipClient();
    }
}
