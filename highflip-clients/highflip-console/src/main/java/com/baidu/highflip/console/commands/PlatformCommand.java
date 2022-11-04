package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import highflip.v1.Highflip;
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
    public Highflip.PlatformGetResponse get() {
        return client.getPlatform();
    }

    @ShellMethod(key = "platform match", value = "Match platform information")
    public void match(String company, String product, String version) {
        throw new UnsupportedOperationException();
    }
}
