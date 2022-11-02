package com.baidu.highflip.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streams {

    public static <T> Stream<T> of(Iterator<T> itorator) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(itorator, Spliterator.ORDERED),
                false);
    }
}
