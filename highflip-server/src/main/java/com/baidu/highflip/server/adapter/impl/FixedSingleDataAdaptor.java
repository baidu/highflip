package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.runtime.Column;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Type;

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
    public Data getData(Data data) {
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
    public long getDataSize(Data data, PositionType type) {
        if (type == PositionType.ROW) {
            return 3;
        }
        return 0;
    }

    @Override
    public Iterator<List<Object>> readData(Data data, PositionType type, long offset, long size) {
        if (type == PositionType.ROW) {
            return DATA_BODY
                    .stream()
                    .skip(offset)
                    .limit(size)
                    .iterator();
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Data createData(Data data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long writeData(Data data, PositionType type, Iterator<List<Object>> body) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteData(Data data) {
        throw new UnsupportedOperationException();
    }
}
