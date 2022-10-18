package com.baidu.highflip.console.result;



import com.baidu.highflip.console.utils.ProtoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.ResultHandler;

public class MessageResultHandler implements ResultHandler<Message> {

    @Autowired
    ObjectMapper mapper;

    Terminal terminal;

    public MessageResultHandler(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void handleResult(Message result) {
        try {
            String json = ProtoUtils.toJsonLine(result);
            terminal.writer().println(json);
        } finally {
            terminal.writer().flush();
        }
    }
}
