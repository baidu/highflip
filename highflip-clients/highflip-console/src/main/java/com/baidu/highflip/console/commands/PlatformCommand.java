package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("platform")
public class PlatformCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "platform get", value = "Get platform information")
    public String get() {
        return client.getPlatform().getVersion().toString();
    }

    @ShellMethod(key = "platform match", value = "Match platform information")
    public void match(String company, String product, String version){

    }

}
