package com.baidu.highflip.client.dataio.reader;

import com.baidu.highflip.client.dataio.DataReader;

import java.io.InputStream;
import java.util.*;

public class CSVReader implements DataReader<String>{

    Scanner input;

    String splitter = ",";

    public static CSVReader from(InputStream input) {
        CSVReader reader = new CSVReader();
        reader.input = new Scanner(input);
        return reader;
    }

    @Override
    public Iterator<List<String>> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return input.hasNext();
            }

            @Override
            public List<String> next() {
                return List.of(input.nextLine().split(splitter));
            }
        };
    }
}
