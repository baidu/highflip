package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.client.model.Column;
import com.baidu.highflip.client.model.Schema;
import com.baidu.highflip.client.reader.CSVReader;
import com.baidu.highflip.client.reader.SVMReader;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            @ShellOption(valueProvider = FileValueProvider.class) String filename) {
        try (OutputStream output = new FileOutputStream(filename)) {
            client.pullDataRaw(dataId).transferTo(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(key = "data push", value = "Push a local raw file to remote server.")
    public String push(
            @ShellOption String name,
            @ShellOption(defaultValue = "raw") String mode,
            @ShellOption(defaultValue = "") String description,
            @ShellOption(defaultValue = "10") int batch,
            @ShellOption(defaultValue = ShellOption.NULL, arity = ShellOption.ARITY_USE_HEURISTICS) String[] columns,
            @ShellOption(valueProvider = FileValueProvider.class) String filename) {

        Schema schema = Schema.builder()
                .setName(name)
                .setDescription(description)
                .setColumns(Arrays.stream(columns == null? new String[0]: columns)
                        .map(Column::valueOf)
                        .collect(Collectors.toList()))
                .build();

        try (InputStream input = new FileInputStream(filename)) {

            if (mode.compareToIgnoreCase("dense") == 0) {
                return client.pushDataDense(schema,
                        CSVReader.from(input).iterator(), batch);
            } else if (mode.compareToIgnoreCase("sparse") == 0) {
                return client.pushDataSparse(schema,
                        SVMReader.from(input).iterator(), batch);
            } else {
                return client.pushDataRaw(schema,
                        input, batch);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
