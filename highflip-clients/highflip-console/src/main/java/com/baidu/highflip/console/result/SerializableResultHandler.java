package com.baidu.highflip.console.result;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ResultHandler;

import java.io.Serializable;

public class SerializableResultHandler implements ResultHandler<Serializable> {

    @Autowired
    ObjectMapper mapper;

    Terminal terminal;

    public SerializableResultHandler(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void handleResult(Serializable result) {
        try {
            String json = mapper.writeValueAsString(result);
            terminal.writer().println(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            terminal.writer().flush();
        }
    }
}
