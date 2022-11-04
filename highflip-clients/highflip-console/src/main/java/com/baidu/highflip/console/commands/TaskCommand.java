package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
@ShellCommandGroup("task")
public class TaskCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "task list", value = "List all task ids")
    public void list(
            @ShellOption(defaultValue = "0") Integer offset,
            @ShellOption(defaultValue = "0") Integer limit) {

        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "task get", value = "Get task information")
    public void get(String taskId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "task status", value = "Check task status")
    public void status(String taskId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "task control", value = "Control task with action")
    public void control(String taskId, String action) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "task log", value = "Show task log")
    public void log(String taskId) {
        throw new UnsupportedOperationException();
    }
}
