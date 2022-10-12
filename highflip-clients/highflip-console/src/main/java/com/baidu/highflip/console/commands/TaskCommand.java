package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("task")
public class TaskCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "task list", value = "List task")
    public void list() {

    }

}
