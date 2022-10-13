package com.baidu.highflip.console.commands;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ShellComponent
@ShellCommandGroup("test")
public class TestCommand {

    @ShellMethod(key = "test add", value = "Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod(key = "test list", value = "List a number range.")
    public List<String> list(int n) {
        List<String> list = IntStream.rangeClosed(1, n)
                .mapToObj(Integer::toString)
                .collect(Collectors.toList());
        return list;
    }

    @ShellMethod(key = "test obj", value = "return object")
    public Item obj(int n) {
        return new Item("hello", 1);
    }

    @Data
    @AllArgsConstructor
    class Item {
        String name;

        Integer age;
    }
}