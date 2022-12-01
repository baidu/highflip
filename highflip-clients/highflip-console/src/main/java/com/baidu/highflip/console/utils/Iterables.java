package com.baidu.highflip.console.utils;

import java.util.Iterator;

public class Iterables {
    public static <T> Iterable<T> of(Iterator<T> iterator) {
        return () -> iterator;
    }
}
