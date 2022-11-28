package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Data;

import javax.persistence.Tuple;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.util.Iterator;
import java.util.List;

public interface DataAdaptor {
    Data updateData(Data data);

    int getDataCount();

    Data getDataByIndex(int index, Data data);

    void deleteData(Data data);

    OutputStream readDataRaw(Data data);

    Iterator<List<Object>> readDataDense(Data data);

    Iterator<List<KeyPair>> readDataSparse(Data data);

    Data createData(Data data);

    void writeDataRaw(Data data, InputStream body);

    void writeDataDense(Data data, Iterator<List<Object>> body);

    void writeDataSparse(Data data, Iterator<List<KeyPair>> body);

    class KeyPair{

        public String key;

        public Object value;
    }
}
