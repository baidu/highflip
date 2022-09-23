package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Data;

import java.util.Iterator;
import java.util.List;

public interface DataAdaptor {
    enum PositionType{
        BEGIN,
        END,
        BYTE,
        CELL,
        ROW,
    }

    Data getData(Data data);

    int getDataCount();

    Data getDataByIndex(int index, Data data);

    long getDataSize(Data data, PositionType type);

    Iterator<List<Object>> readData(Data data, PositionType type, long offset, long size);

    Data createData(Data data);

    long writeData(Data data, PositionType type, Iterator<List<Object>> body);

    void deleteData(Data data);
}
