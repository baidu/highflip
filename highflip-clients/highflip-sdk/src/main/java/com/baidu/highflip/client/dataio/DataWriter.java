package com.baidu.highflip.client.dataio;

import java.util.Iterator;
import java.util.List;

public interface DataWriter<T> {

    void write(Iterator<List<T>> iter);
}
