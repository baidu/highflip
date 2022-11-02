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
@ShellCommandGroup("partner")
public class PartnerCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "partner get", value = "Get partner information")
    public Highflip.PartnerGetResponse get(String partnerId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "partner list", value = "List partner ids")
    public Iterable<String> list() {
        throw new UnsupportedOperationException();
    }
}
