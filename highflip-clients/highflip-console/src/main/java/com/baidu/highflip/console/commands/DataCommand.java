package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.client.reader.CSVReader;
import com.baidu.highflip.client.reader.SVMReader;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.*;

import java.io.*;
import java.util.List;

@Slf4j
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
    public Iterable<List<String>> pull(
            @ShellOption String dataId) {
        return () -> client.pullDataDense(dataId);
    }

    @ShellMethod(key = "data pull raw", value = "Pull a raw data to local file.")
    public void pullRaw(
            @ShellOption String dataId,
            @ShellOption(valueProvider = FileValueProvider.class)String filename) {
        try (OutputStream output = new FileOutputStream(filename)) {
            client.pullDataRaw(dataId).transferTo(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(key = "data push", value = "Push a local raw file to remote server.")
    public String push(
            @ShellOption String name,
            @ShellOption(defaultValue = "raw", value = {"raw", "dense", "sparse"}) String format,
            @ShellOption(defaultValue = "") String description,
            @ShellOption(arity = ShellOption.ARITY_USE_HEURISTICS) String[] columns,
            @ShellOption(valueProvider = FileValueProvider.class) String filename) {

        try (InputStream input = new FileInputStream(filename)) {

            switch (format){
                default:
                case "raw":
                    return client.pushDataRaw(name, description,
                            input);
                case "dense":
                    return client.pushDataDense(name, description,
                            CSVReader.from(input).iterator());
                case "sparse":
                    return client.pushDataSparse(name, description,
                            SVMReader.from(input).iterator());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
