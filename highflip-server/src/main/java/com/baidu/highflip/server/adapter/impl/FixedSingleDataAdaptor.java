package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.basic.Column;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.baidu.highflip.core.entity.runtime.basic.Type;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class FixedSingleDataAdaptor implements DataAdaptor {

    public static final String DATA_NAME = "test_data";

    public static final List DATA_BODY = List.of(
            List.of(1, "alice", 1.1),
            List.of(2, "bob", 2.2),
            List.of(3, "can", 3.3)
    );

    public static final List<Column> DATA_COLUMNS = List.of(
            new Column(1, "id", Type.INT, ""),
            new Column(2, "name", Type.STRING, ""),
            new Column(3, "score", Type.FLOAT, "")
    );

    @Override
    public Data updateData(Data data) {
        data.setName(data.getName());
        data.setColumns(DATA_COLUMNS);
        return null;
    }

    @Override
    public int getDataCount() {
        return 1;
    }

    @Override
    public Data getDataByIndex(int index, Data data) {
        data.setName(data.getName());
        data.setColumns(DATA_COLUMNS);
        return data;
    }

    @Override
    public InputStream readDataRaw(Data data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<List<Object>> readDataDense(Data data) {

        return DATA_BODY
                .stream()
                .iterator();
    }

    @Override
    public Iterator<List<KeyPair>> readDataSparse(Data data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Data createData(Data data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeDataRaw(Data data, InputStream body) {

    }

    @Override
    public void writeDataDense(Data data, Iterator<List<Object>> body) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeDataSparse(Data data, Iterator<List<KeyPair>> body) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteData(Data data) {
        throw new UnsupportedOperationException();
    }
}
