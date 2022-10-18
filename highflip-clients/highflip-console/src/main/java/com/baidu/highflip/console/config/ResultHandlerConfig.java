package com.baidu.highflip.console.config;

import com.baidu.highflip.console.result.LinesResultHandler;
import com.baidu.highflip.console.result.MessageResultHandler;
import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ResultHandlerConfig {

    @Bean
    LinesResultHandler iterableResultHandler(Terminal terminal) {
        return new LinesResultHandler(terminal);
    }

    @Bean
    MessageResultHandler messageResultHandler(Terminal terminal) {
        return new MessageResultHandler(terminal);
    }
}
