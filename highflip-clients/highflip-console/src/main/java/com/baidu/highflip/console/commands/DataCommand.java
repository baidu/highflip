package com.baidu.highflip.console.commands;

import com.baidu.highflip.client.HighFlipClient;
import com.baidu.highflip.client.model.Column;
import com.baidu.highflip.client.model.KeyPair;
import com.baidu.highflip.client.model.Schema;
import com.baidu.highflip.client.dataio.reader.CSVReader;
import com.baidu.highflip.client.dataio.reader.LibSVMReader;
import com.baidu.highflip.client.utils.Streams;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.*;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
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
    public Highflip.DataGetResponse get(
            @ShellOption(value = {"-i", "--dataid"}) String dataId) {
        return client.getData(dataId);
    }

    @ShellMethod(key = "data delete", value = "Delete a remote data")
    public void delete(
            @ShellOption(value = {"-i", "--dataid"}) String dataId) {

        client.deleteData(dataId);
    }

    @ShellMethod(key = "data pull", value = "Pull a data to local")
    public Iterable<List<String>> pull(
            @ShellOption(value = {"-i", "--dataid"})
                    String dataId,
            @ShellOption(value = {"-m", "--mode"},
                    help = "value of raw/dense/sparse",
                    defaultValue = "dense")
                    String mode,
            @ShellOption(value = {"-b", "--batch"},
                    defaultValue = "1024")
                    int batch,
            @ShellOption(value = {"-f", "--filename"},
                    defaultValue = ShellOption.NULL,
                    valueProvider = FileValueProvider.class)
                    String filename) throws IOException {

        if(filename == null){
            if (mode.equalsIgnoreCase("dense")) {
                return () -> client.pullDataDense(dataId, batch);
            } else if (mode.equalsIgnoreCase("sparse")) {
                return () -> Streams.of(client.pullDataSparse(dataId, batch))
                        .map(r -> r.stream()
                                .map(KeyPair::toString)
                                .collect(Collectors.toList()))
                        .iterator();
            } else {
                throw new UnsupportedOperationException(mode);
            }
        } else {
            if (mode.equalsIgnoreCase("raw")){
                try (OutputStream output = new FileOutputStream(filename)) {
                    client.pullDataRaw(dataId, batch).transferTo(output);
                }
                return List.of();
            } else {
                throw new UnsupportedOperationException(mode);
            }
        }
    }

    @ShellMethod(key = "data push", value = "Push a local raw file to remote server.")
    public String push(
        @ShellOption(value = {"-n", "--name"})
                String name,
        @ShellOption(value = {"-m", "--mode"},
                help = "value of raw/dense/sparse",
                defaultValue = "raw")
                String mode,
        @ShellOption(value = {"-d", "--desc"},
                defaultValue = "")
                String description,
        @ShellOption(value = {"-b", "--batch"},
                defaultValue = "64") int batch,
        @ShellOption(value = {"-c", "--column"},
                help = "column definition, follow name:type format.",
                arity = ShellOption.ARITY_USE_HEURISTICS,
                defaultValue = ShellOption.NULL)
                String[] columns,
        @ShellOption(value = {"-f", "--filename"},
                valueProvider = FileValueProvider.class)
                String filename)
        throws IOException {

        Schema schema = Schema.builder()
                .setName(name)
                .setDescription(description)
                .setColumns(Arrays.stream(columns == null? new String[0]: columns)
                        .map(Column::valueOf)
                        .collect(Collectors.toList()))
                .build();

        try (InputStream input = new FileInputStream(filename)) {

            if (mode.equalsIgnoreCase("dense")) {
                return client.pushDataDense(schema,
                        CSVReader.from(input).iterator(), batch);
            } else if (mode.equalsIgnoreCase("sparse")) {
                return client.pushDataSparse(schema,
                        LibSVMReader.from(input).iterator(), batch);
            } else if (mode.equalsIgnoreCase("raw")){
                return client.pushDataRaw(schema,
                        input, batch);
            } else {
                throw new UnsupportedOperationException(mode);
            }
        }
    }
}
