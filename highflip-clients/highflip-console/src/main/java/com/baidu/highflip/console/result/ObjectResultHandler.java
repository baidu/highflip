package com.baidu.highflip.console.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ResultHandler;

public class ObjectResultHandler implements ResultHandler<Object> {

    @Autowired
    ObjectMapper mapper;

    Terminal terminal;

    public ObjectResultHandler(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void handleResult(Object result) {
        try {
            String line = mapper.writeValueAsString(result);
            terminal.writer().println(line);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
