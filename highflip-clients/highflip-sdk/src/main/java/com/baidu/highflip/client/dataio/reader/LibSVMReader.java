package com.baidu.highflip.client.dataio.reader;

import com.baidu.highflip.client.dataio.DataReader;
import com.baidu.highflip.client.model.KeyPair;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class LibSVMReader implements DataReader<KeyPair> {

    Scanner input;

    String label_key = "LABEL";

    String splitter = " ";

    String kv_splitter = ":";

    public static LibSVMReader from(InputStream input){
        LibSVMReader reader = new LibSVMReader();
        reader.input = new Scanner(input);
        return reader;
    }

    KeyPair toKeyPair(String value){
        String[] items = value.split(kv_splitter, 2);
        return KeyPair.of(items[0], items[1]);
    }

    List<KeyPair> toRow(String line){
        List<String> items = Arrays.stream(line.split(splitter))
                .filter(n -> n.length() != 0)
                .collect(Collectors.toList());

        List<KeyPair> row = new ArrayList<>(items.size());

        row.add(KeyPair.of(label_key, items.get(0)));
        items.stream().skip(1).forEach(v -> {
            row.add(toKeyPair(v));
        });

        return row;
    }

    @Override
    public Iterator<List<KeyPair>> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return input.hasNext();
            }

            @Override
            public List<KeyPair> next() {
                return toRow(input.nextLine());
            }
        };
    }
}
