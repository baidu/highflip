package com.baidu.highflip.console.result;


import com.baidu.highflip.console.utils.ProtoUtils;
import com.google.protobuf.Message;
import org.jline.terminal.Terminal;
import org.springframework.shell.ResultHandler;

public class MessageResultHandler implements ResultHandler<Message> {

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
