package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.basic.Column;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.baidu.highflip.core.entity.runtime.basic.Type;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class FixedSingleDataAdaptor implements DataAdaptor {

    public static final String DATA_NAME = "test_data";

    public static final List<List<Object>> DATA_BODY = List.of(
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
        StringBuffer output = new StringBuffer();
        DATA_BODY.stream()
                .map(r -> r.stream().map(Object::toString).collect(Collectors.toList()))
                .map(r -> String.join(",", r))
                .forEach(l -> output.append(l + "\n"));

        return new ByteArrayInputStream(output.toString().getBytes());
    }

    @Override
    public Iterator<List<Object>> readDataDense(Data data) {
        return DATA_BODY
                .stream()
                .iterator();
    }

    @Override
    public Iterator<List<KeyPair>> readDataSparse(Data data) {
        return DATA_BODY
                .stream()
                .map(r -> IntStream.range(0, DATA_COLUMNS.size())
                        .mapToObj(i -> KeyPair.of(DATA_COLUMNS.get(i).getName(), r.get(i)))
                        .collect(Collectors.toList()))
                .iterator();
    }

    @Override
    public Data createData(Data data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeDataRaw(Data data, InputStream body) {
        int size = 0;
        byte[] buff = new byte[1024];
        try {
            while (true) {
                int done = body.read(buff);
                if (done < 0) {
                    break;
                }

                log.info("drop raw data, name:{}, offset:{}, size:{}",
                        data.getName(), size, done);

                size += done;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("done raw data, name:{}, size:{}",
                data.getName(), size);
    }

    @Override
    public void writeDataDense(Data data, Iterator<List<Object>> body) {
        int size = 0;

        while (body.hasNext()) {
            var row = body.next();

            log.info("drop dense data, name:{}, size:{}",
                    data.getName(), row.size());

            size += 1;
        }

        log.info("done dense data, name:{}, rows:{}",
                data.getName(), size);
    }

    @Override
    public void writeDataSparse(Data data, Iterator<List<KeyPair>> body) {
        int size = 0;

        while (body.hasNext()) {
            var row = body.next();

            log.info("drop sparse data, name:{}, size:{}",
                    data.getName(), row.size());

            size += 1;
        }
        ;

        log.info("done sparse data, name:{}, rows:{}",
                data.getName(), size);
    }

    @Override
    public void deleteData(Data data) {
        throw new UnsupportedOperationException();
    }
}
