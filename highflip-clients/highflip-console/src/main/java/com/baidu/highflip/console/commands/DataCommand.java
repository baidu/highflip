package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
@ShellCommandGroup("data")
public class DataCommand {

    @Autowired
    HighFlipClient client;

}
