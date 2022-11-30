package com.baidu.highflip.client.reader;

import com.baidu.highflip.client.model.KeyPair;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SVMReader implements Iterable<List<KeyPair>>{

    Scanner input;

    String splitter = " ";

    String kv_splitter = ":";

    public static SVMReader from(InputStream input){
        SVMReader reader = new SVMReader();
        reader.input = new Scanner(input);
        return reader;
    }

    KeyPair toKeyPair(String value){
        String[] items = value.split(kv_splitter);
        return KeyPair.of(items[0], items[1]);
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
                return Arrays.stream(input.next().split(splitter))
                        .map(kv -> toKeyPair(kv))
                        .collect(Collectors.toList());
            }
        };
    }
}
