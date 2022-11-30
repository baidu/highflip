package com.baidu.highflip.client.reader;

import java.io.InputStream;
import java.util.*;

public class CSVReader implements Iterable<List<String>>{

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
                return List.of(input.next().split(splitter));
            }
        };
    }
}
