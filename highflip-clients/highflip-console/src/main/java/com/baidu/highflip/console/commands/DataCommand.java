package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import highflip.v1.Highflip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ShellComponent
@ShellCommandGroup("data")
public class DataCommand {

    @Autowired
    HighFlipClient client;

    @ShellMethod(key = "data list", value = "List data ids")
    public Iterable<String> list(
            @ShellOption(defaultValue = "0") Integer offset,
            @ShellOption(defaultValue = "0") Integer limit) {
        return client.listData(offset, limit);
    }

    @ShellMethod(key = "data get", value = "Get a data basic information")
    public Highflip.DataGetResponse get(String dataId) {
        return client.getData(dataId);
    }

    @ShellMethod(key = "data delete", value = "Delete a remote data")
    public void delete(String dataId) {
        client.deleteData(dataId);
    }

    @ShellMethod(key = "data pull", value = "Pull a data to local")
    public Iterable<String> pull(String dataId) {
        throw new UnsupportedOperationException();
    }

    @ShellMethod(key = "data pull raw", value = "Pull a raw data to local file.")
    public void pullRaw(String dataId, String filename) {
        try (OutputStream output = new FileOutputStream(filename)) {
            client.pullDataRaw(dataId).transferTo(output);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @ShellMethod(key = "data push", value = "Push a local raw file to remote server.")
    public String pushRaw(
            @ShellOption String name,
            @ShellOption String description,
            @ShellOption String file) {
        throw new UnsupportedOperationException();
    }
}
