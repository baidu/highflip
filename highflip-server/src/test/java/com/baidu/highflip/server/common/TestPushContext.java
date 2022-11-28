package com.baidu.highflip.server.common;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.server.engine.common.PushContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
@ActiveProfiles({"disable-security"})
public class TestPushContext {

    @Test
    public void testDense() throws InterruptedException {
        DataAdaptor adaptor = new TestDataAdaptor() {
            @Override
            public void writeDataDense(Data data, Iterator<List<Object>> body) {
                Iterable<List<Object>> iter = () -> body;
                for (List<Object> row : iter) {
                    log.info("write = {}", row);
                }
            }
        };

        try (PushContext context = PushContext.createDense(adaptor, new Data())) {
            int round = new Random().nextInt(10) + 1;
            for (int i = 0; i < round; i++) {
                List<Object> row = List.of(i, 1, 2, 3);
                log.info("push = {}", row);
                context.pushDense(row);
            }
        }
    }

}


class TestDataAdaptor implements DataAdaptor {

    @Override
    public Data updateData(Data data) {
        return null;
    }

    @Override
    public int getDataCount() {
        return 0;
    }

    @Override
    public Data getDataByIndex(int index, Data data) {
        return null;
    }

    @Override
    public void deleteData(Data data) {

    }

    @Override
    public OutputStream readDataRaw(Data data) {
        return null;
    }

    @Override
    public Iterator<List<Object>> readDataDense(Data data) {
        return null;
    }

    @Override
    public Iterator<List<DataAdaptor.KeyPair>> readDataSparse(Data data) {
        return null;
    }

    @Override
    public Data createData(Data data) {
        return null;
    }

    @Override
    public void writeDataRaw(Data data, InputStream body) {

    }

    @Override
    public void writeDataDense(Data data, Iterator<List<Object>> body) {

    }

    @Override
    public void writeDataSparse(Data data, Iterator<List<KeyPair>> body) {

    }
}

