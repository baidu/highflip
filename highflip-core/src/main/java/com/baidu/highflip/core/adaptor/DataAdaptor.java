package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public interface DataAdaptor {
    Data updateData(Data data);

    int getDataCount();

    Data getDataByIndex(int index, Data data);

    void deleteData(Data data);

    InputStream readDataRaw(Data data);

    Iterator<List<Object>> readDataDense(Data data);

    Iterator<List<KeyPair>> readDataSparse(Data data);

    Data createData(Data data);

    void writeDataRaw(Data data, InputStream body);

    void writeDataDense(Data data, Iterator<List<Object>> body);

    void writeDataSparse(Data data, Iterator<List<KeyPair>> body);

}
