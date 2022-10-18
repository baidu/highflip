package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.console.config.GitVersionConfig;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.commands.Version;

import javax.annotation.PreDestroy;

@Slf4j
@ShellComponent
@ShellCommandGroup("basic")
public class BasicCommand implements Version.Command {

    @Autowired
    HighFlipClient client;

    @Autowired
    GitVersionConfig gitVersion;

    String serverUrl = null;

    @PreDestroy
    public void destroy() {
        if (!client.isConnected()) {
            return;
        }

        log.info("disconnect from HighFlip server.");
        client.close();
    }

    @ShellMethod(key = "connect", value = "Connect to HighFlip server.")
    public void connect(
            @ShellOption(defaultValue = "grpc://127.0.0.1:8751") String url) {

        log.info("connect to HighFlip server: {}", url);
        client.connect(url);

        this.serverUrl = url;
    }

    @ShellMethod(key = "disconnect", value = "Disconnect from HighFlip server.")
    public void disconnect() {
        client.close();
    }

    @ShellMethod(key = "reconnect", value = "reconnect from HighFlip server.")
    public void reconnect() {
        if (Strings.isNullOrEmpty(this.serverUrl)) {
            return;
        }

        log.info("reconnect to HighFlip server: {}", this.serverUrl);
        client.connect(this.serverUrl);
    }

    @ShellMethod(key = "version", value = "Show client version information.")
    public GitVersionConfig version() {
        return gitVersion;
    }
}
