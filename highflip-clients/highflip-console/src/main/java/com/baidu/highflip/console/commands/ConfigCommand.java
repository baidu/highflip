package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@Slf4j
@ShellComponent
@ShellCommandGroup("config")
public class ConfigCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "config get", value = "Get config value")
    public Highflip.ConfigGetResponse get(String key) {
        return client.getConfig(key);
    }

    @ShellMethod(key = "config set", value = "Set config value")
    public void set(String key, String value) {
        client.setConfig(key, value);
    }

    @ShellMethod(key = "config list", value = "List config keys")
    public Iterable<String> list() {
        return client.listConfig();
    }

    @ShellMethod(key = "config delete", value = "Delete config entry")
    public void delete(String key) {
        client.deleteConfig(key);
    }
}
