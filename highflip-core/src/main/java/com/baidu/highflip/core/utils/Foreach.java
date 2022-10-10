package com.baidu.highflip.core.utils;

import java.util.Iterator;

public class Foreach<T> implements Iterable<T> {
    Iterator<T> itor;

    private Foreach(Iterator<T> itor) {
        this.itor = itor;
    }

    public static <T> Foreach<T> from(Iterator<T> itor) {
        return new Foreach(itor);
    }

    @Override
    public Iterator<T> iterator() {
        return itor;
    }
}
