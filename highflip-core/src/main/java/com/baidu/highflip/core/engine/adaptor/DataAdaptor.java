package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Data;

import java.util.Iterator;
import java.util.List;

public interface DataAdaptor {

    List<String> getFeatures();

    Data getData(Data data);

    int getCount();

    Data getDataByIndex(int index, Data data);

    Iterator<List> readData(Data data, long offset, long size);

    Data createData(Data data);

    void writeData(Data data, Iterator<List> body);

    void deleteData(Data data);
}
