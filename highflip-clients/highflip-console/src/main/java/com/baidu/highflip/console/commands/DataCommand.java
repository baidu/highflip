package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.client.model.Column;
import com.baidu.highflip.client.model.Schema;
import com.baidu.highflip.client.dataio.reader.CSVReader;
import com.baidu.highflip.client.dataio.reader.LibSVMReader;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public Iterable<List<Object>> pull(
            @ShellOption String dataId,
            @ShellOption(defaultValue = "1024") int batch) {
        return () -> client.pullDataObject(dataId);
    }

    @ShellMethod(key = "data pullfile", value = "Pull a raw data to local file.")
    public void pullFile(
            @ShellOption String dataId,
            @ShellOption(defaultValue = "1024") int batch,
            @ShellOption(valueProvider = FileValueProvider.class) String filename) {
        try (OutputStream output = new FileOutputStream(filename)) {
            client.pullDataRaw(dataId, batch).transferTo(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(key = "data pushfile", value = "Push a local raw file to remote server.")
    public String pushFile(
        @ShellOption(value = {"-n", "--name"})
            String name,
        @ShellOption(value = {"-m", "--mode"},
            help = "value of raw/dense/sparse",
            defaultValue = "raw") String mode,
        @ShellOption(defaultValue = "") String description,
        @ShellOption(value = {"-b", "--batch"},
                defaultValue = "10") int batch,
        @ShellOption(value = {"-c", "--column"},
            help = "column definition, follow name:type format.",
            arity = ShellOption.ARITY_USE_HEURISTICS,
                defaultValue = ShellOption.NULL) String[] columns,
        @ShellOption(value = {"-f", "--filename"},
            valueProvider = FileValueProvider.class) String filename) {

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
                        LibSVMReader.from(input).iterator(), batch);
            } else {
                return client.pushDataRaw(schema,
                        input, batch);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
