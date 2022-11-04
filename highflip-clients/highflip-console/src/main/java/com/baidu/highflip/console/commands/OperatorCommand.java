package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
@ShellCommandGroup("operator")
public class OperatorCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "operator get", value = "Get operator information.")
    public Highflip.OperatorGetResponse get(String operatorId) {
        return client.getOperator(operatorId);
    }

    @ShellMethod(key = "operator list", value = "List all operator ids.")
    public Iterable<String> list(
            @ShellOption(defaultValue = "0") Integer offset,
            @ShellOption(defaultValue = "0") Integer limit) {
        return client.listOperators(offset, limit);
    }
}
