package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@Slf4j
@ShellComponent
@ShellCommandGroup("operator")
public class OperatorCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "operator get", value = "Get operator information.")
    public String get(String operatorId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "operator list", value = "List all operator ids.")
    public Iterable<String> list() {
        throw new UnsupportedOperationException();
    }

}
