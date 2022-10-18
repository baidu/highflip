package com.baidu.highflip.console.result;

import org.jline.terminal.Terminal;
import org.springframework.shell.ResultHandler;

import java.io.PrintWriter;

public class LinesResultHandler implements ResultHandler<Iterable> {

    Terminal terminal;

    public LinesResultHandler(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void handleResult(Iterable result) {
        PrintWriter writer = this.terminal.writer();
        for (Object item : result) {
            writer.println(item.toString());
        }
        writer.flush();
    }
}
