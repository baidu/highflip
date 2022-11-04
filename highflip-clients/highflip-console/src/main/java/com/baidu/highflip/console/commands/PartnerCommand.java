package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
@ShellCommandGroup("partner")
public class PartnerCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "partner add", value = "Create a partner.")
    public String create(String name, String description) {
        return client.createPartner(name, description);
    }

    @ShellMethod(key = "partner get", value = "Get a partner information.")
    public Highflip.PartnerGetResponse get(String partnerId) {
        return client.getPartner(partnerId);
    }

    @ShellMethod(key = "partner list", value = "List partner ids.")
    public Iterable<String> list(
            @ShellOption(defaultValue = "0") Integer offset,
            @ShellOption(defaultValue = "0") Integer limit) {
        return client.listPartners(offset, limit);
    }
}
