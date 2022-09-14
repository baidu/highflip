package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Data;

import java.util.Iterator;
import java.util.List;

public interface DataAdaptor {

    Data getData(String dataid);

    Iterator<List> readData(String dataid, long offset, long size);

    void createData(String name);

    void writeData(String dataid, Iterator<List> data);
}
