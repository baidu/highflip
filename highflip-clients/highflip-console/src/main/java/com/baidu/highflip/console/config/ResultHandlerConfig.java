package com.baidu.highflip.console.config;

import com.baidu.highflip.console.result.LinesResultHandler;
import com.baidu.highflip.console.result.MessageResultHandler;
import com.baidu.highflip.console.result.SerializableResultHandler;
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
    MessageResultHandler messageResultHandler(Terminal terminal) {
        return new MessageResultHandler(terminal);
    }

    // @Bean
    SerializableResultHandler serializableResultHandler(Terminal terminal) {
        return new SerializableResultHandler(terminal);
    }
}
