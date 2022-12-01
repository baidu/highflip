package com.baidu.highflip.client.dataio;

import com.baidu.highflip.client.dataio.reader.CSVReader;
import com.baidu.highflip.client.dataio.reader.LibSVMReader;
import com.baidu.highflip.client.model.KeyPair;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class TestReader {

    @Test
    public void testCSVReader() throws IOException {

        int offset = 0;
        try(InputStream input = getClass()
                .getResourceAsStream("/data/iris.csv")){

            for(List<String> row: CSVReader.from(input)){
                log.info("offset: {}, csv: {}", offset++, row);
                Assertions.assertEquals(6, row.size());
            }
        }
        Assertions.assertEquals(151, offset);
    }

    @Test
    public void testLibSVMReader() throws IOException {
        int offset = 0;
        try(InputStream input = getClass()
                .getResourceAsStream("/data/diabetes.libsvm")){

            for(List<KeyPair> row: LibSVMReader.from(input)){
                log.info("offset: {}, libsvm: {}", offset++, row);
                Assertions.assertEquals(9, row.size());
            }
        }
        Assertions.assertEquals(768, offset);
    }
}
