package com.baidu.highflip.console.commands;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("test")
public class TestCommand {

    @ShellMethod(key = "test add", value = "Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}