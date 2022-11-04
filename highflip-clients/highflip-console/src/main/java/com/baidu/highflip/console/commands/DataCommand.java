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

    @ShellMethod(key = "data list", value = "List data ids")
    public Iterable<String> list() {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data get", value = "Get a data basic information")
    public Iterable<String> get(String dataId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data delete", value = "Delete a remote data")
    public void delete(String dataId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data pull", value = "Pull a data to local")
    public Iterable<String> pull(String dataId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data push", value = "Push a data to remote")
    public Iterable<String> push(String dataId) {
        throw new UnsupportedOperationException();
    }
}
