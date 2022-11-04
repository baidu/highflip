package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import highflip.HighflipMeta;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
@ShellCommandGroup("job")
public class JobCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "job list", value = "List all job ids.")
    public Iterable<String> list(
            @ShellOption(defaultValue = "0") Integer offset,
            @ShellOption(defaultValue = "0") Integer limit) {

        return client.listJob(0, 0);
    }

    @ShellMethod(key = "job get", value = "Get a job information by JobId")
    public Highflip.JobGetResponse get(
            @ShellOption String jobId) {
        return client.getJob(jobId);
    }

    @ShellMethod(key = "job add", value = "Submit a new job.")
    public String add(
            @ShellOption String name,
            @ShellOption(defaultValue = "") String description,
            @ShellOption String dag) {

        HighflipMeta.GraphProto graph = HighflipMeta.GraphProto
                .newBuilder()
                .build();

        return client.createJob(name, description, graph);
    }

    @ShellMethod(key = "job delete", value = "Delete a existed job.")
    public void delete(
            @ShellOption String jobId) {

        client.deleteJob(jobId);
    }

    @ShellMethod(key = "job status", value = "Check job status.")
    public String status(
            @ShellOption String jobId) {

        return client.checkJob(jobId);
    }

    @ShellMethod(key = "job control", value = "Control job with an specified action.")
    public void control(
            @ShellOption String jobId,
            @ShellOption String action) {

        client.controlJob(jobId, action);
    }
}
