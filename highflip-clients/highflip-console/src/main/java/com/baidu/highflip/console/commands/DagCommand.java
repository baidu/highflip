package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@Slf4j
@ShellComponent
@ShellCommandGroup("dag")
public class DagCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "dag tojson", value = "Convert DAG from protobuf to json format.")
    public String tojson(String path) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "dag fromjson", value = "Convert DAG from json to protobuf format.")
    public String fromjson(String json, String output) {
        throw new UnsupportedOperationException();
    }
}
