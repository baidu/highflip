package com.baidu.highflip.client.dataio;

import com.baidu.highflip.client.dataio.writer.CSVWriter;
import com.baidu.highflip.client.dataio.writer.LibSVMWriter;
import com.baidu.highflip.client.model.KeyPair;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class TestWriter {

    @TempDir
    Path tempDir;

    @Test
    public void testCSVWriter() throws IOException{
        var data = List.of(
                List.of("1", "0", "0", "0"),
                List.of("2", "0", "0", "0"),
                List.of("3", "0", "0", "0"));

        File filename = tempDir.resolve("test.csv").toFile();
        log.info("csv filename: {}", filename);

        try(OutputStream output = new FileOutputStream(filename)){
            CSVWriter.from(output)
                    .write(data.iterator());
            log.info("csv write done.");
        }

        var lines = Files.readAllLines(filename.toPath());

        int col = lines.get(0).split(",").length;
        log.info("csv row count: {}", col);
        Assertions.assertEquals(data.get(0).size(), col);

        int row = lines.size();
        log.info("csv row count: {}", row);
        Assertions.assertEquals(data.size(), row);
    }

    @Test
    public void testLibSVMWriter() throws IOException{
        var data = List.of(
            List.of(KeyPair.of("LABEL", "+1"), KeyPair.of("id", "1"), KeyPair.of("x1", "0"), KeyPair.of("x2", "0")),
            List.of(KeyPair.of("LABEL", "-1"), KeyPair.of("id", "2"), KeyPair.of("x1", "0"), KeyPair.of("x2", "0")),
            List.of(KeyPair.of("LABEL", "+1"), KeyPair.of("id", "3"), KeyPair.of("x1", "0"), KeyPair.of("x2", "0")));

        File filename = tempDir.resolve("test.libsvm").toFile();
        log.info("libsvm filename: {}", filename);

        try(OutputStream output = new FileOutputStream(filename)){
            LibSVMWriter.from(output)
                    .write(data.iterator());
            log.info("libsvm write done.");
        }

        var lines = Files.readAllLines(filename.toPath());

        int col = lines.get(0).split(" ").length;
        log.info("libsvm row count: {}", col);
        Assertions.assertEquals(data.get(0).size() + 1, col);

        int row = lines.size();
        log.info("libsvm row count: {}", row);
        Assertions.assertEquals(data.size(), row);
    }
}
