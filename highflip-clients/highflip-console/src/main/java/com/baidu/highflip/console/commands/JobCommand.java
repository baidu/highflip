package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@ShellCommandGroup("job")
public class JobCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "job list", value = "Submit a new job.")
    public void list() {

    }

    @ShellMethod(key = "job add", value = "Submit a new job.")
    public String add(String name, String description, String dag) {
        return client.createJob(name, description, null);
    }

    @ShellMethod(key = "job check", value = "Check job status.")
    public String check(String jobId) {
        return client.checkJob(jobId);
    }

    @ShellMethod(key = "job control", value = "Control job with an specified action.")
    public void control(String jobId, String action) {
        client.controlJob(jobId, action);
    }
}
