package com.baidu.highflip.utils;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streams {

    public static <T> Stream<T> of(Iterator<T> iterator) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                false);
    }

    public static <T> Iterator<List<T>> toBatch(Iterator<T> inputs, int batch) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return inputs.hasNext();
            }

            @Override
            public List<T> next() {
                List<T> next = new ArrayList<>(batch);
                for (int i = 0; i < batch && inputs.hasNext(); i++) {
                    next.add(inputs.next());
                }
                return next;
            }
        };
    }
}
