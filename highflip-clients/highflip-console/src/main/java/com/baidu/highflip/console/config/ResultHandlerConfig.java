package com.baidu.highflip.console.config;

import com.baidu.highflip.console.result.LinesResultHandler;
import com.baidu.highflip.console.result.ObjectResultHandler;
import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultHandlerConfig {

    @Bean
    LinesResultHandler iterableResultHandler(Terminal terminal) {
        return new LinesResultHandler(terminal);
    }

    @Bean
    ObjectResultHandler objectResultHandler(Terminal terminal) {
        return new ObjectResultHandler(terminal);
    }
}
