package com.baidu.highflip.server.config;

import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.server.engine.register.MapRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EngineConfig {

    @Bean
    InstanceRegister getRegister(){
        return new MapRegister();
    }
}
