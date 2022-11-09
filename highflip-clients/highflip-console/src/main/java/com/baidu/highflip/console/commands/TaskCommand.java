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
@ShellCommandGroup("task")
public class TaskCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "task list", value = "List all task ids")
    public Iterable<String> list(
            @ShellOption(defaultValue = "0") Integer offset,
            @ShellOption(defaultValue = "0") Integer limit) {

        return client.listTasks(offset, limit);
    }

    @ShellMethod(key = "task get", value = "Get a task information")
    public Highflip.TaskGetResponse get(String taskId) {
        return client.getTask(taskId);
    }

    @ShellMethod(key = "task status", value = "Check a task status")
    public String status(String taskId) {
        return client.checkTask(taskId);
    }

    @ShellMethod(key = "task control", value = "Control a task with action")
    public void control(String taskId, String action) {
        client.controlTask(taskId, action);
    }

    @ShellMethod(key = "task log", value = "Show task logs")
    public Iterable<String> log(String taskId) {
        return client.getTaskLog(taskId);
    }
}
