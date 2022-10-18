package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.console.config.GitVersionConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.commands.Version;

@Slf4j
@ShellComponent
@ShellCommandGroup("basic")
public class BasicCommand implements Version.Command {

    @Autowired
    HighFlipClient client;

    @Autowired
    GitVersionConfig gitVersion;

    @ShellMethod(key = "connect", value = "Connect to HighFlip server.")
    public void connect(
            @ShellOption(defaultValue = "grpc://127.0.0.1:8751") String url) {

        log.info("connect to HighFlip server: {}", url);
        client.connect(url);
    }

    @ShellMethod(key = "disconnect", value = "Disconnect from HighFlip server.")
    public void disconnect() {
        client.close();
    }

    @ShellMethod(key = "version", value = "Show client version information.")
    public GitVersionConfig version() {
        return gitVersion;
    }
}
