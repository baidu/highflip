package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("data")
public class DataCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "data get", value = "Get data basic information")
    public Iterable<String> get(String dataId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data pull", value = "Get data basic information")
    public Iterable<String> pull(String dataId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data push", value = "Get data basic information")
    public Iterable<String> push(String dataId) {
        throw new UnsupportedOperationException();
    }
}
