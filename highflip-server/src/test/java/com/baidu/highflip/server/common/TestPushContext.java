package com.baidu.highflip.server.common;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.baidu.highflip.server.engine.dataio.PushContext;
import highflip.HighflipMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
@ActiveProfiles({"disable-security"})
public class TestPushContext {

    @Test
    public void testRaw() throws InterruptedException {
        int batch = 4;
        int round = new Random().nextInt(100) + 1;
        log.info("test raw, round: {}", round);

        final int[] size = {0, 0};

        DataAdaptor adaptor = new TestDataAdaptor() {
            @Override
            public void writeDataRaw(Data data, InputStream body) {


                try {
                    byte[] buff = new byte[16];
                    while (true) {
                        int done = body.read(buff);
                        if (done < 0) {
                            break;
                        } else {
                            log.info("offset: {}, write = byte {}, ", size[0], buff);
                            size[0] += done;
                        }
                    }
                    log.info("size: {}, write = done", size[0]);
                } catch (IOException e) {
                    log.error("offset: {}, write = except", size[0], e);
                }
            }
        };

        try (PushContext context = PushContext.createRaw(adaptor, new Data())) {
            for (byte i = 0; i < round; i++) {
                byte[] row = new byte[]{i, 0, 0, 0};
                log.info("offset: {}, push = byte {}", size[1], row);
                context.pushRaw(row);

                size[1] += batch;
            }

            log.info("offset: {}, push = done", size[1]);
        }
        log.info("all done");

        Assertions.assertEquals(size[0], size[1]);
    }

    @Test
    public void testDense() throws InterruptedException {
        int round = new Random().nextInt(10) + 1;
        log.info("test dense, round: {}", round);

        final int[] size = {0, 0};

        DataAdaptor adaptor = new TestDataAdaptor() {
            @Override
            public void writeDataDense(Data data, Iterator<List<Object>> body) {
                Iterable<List<Object>> iter = () -> body;
                for (List<Object> row : iter) {
                    log.info("offset: {}, write = dense {}", size[0], row);

                    size[0] += 1;
                }
                log.info("offset: {}, write = done", size[0]);
            }
        };

        try (PushContext context = PushContext.createDense(adaptor, new Data())) {
            for (int i = 0; i < round; i++) {
                List<Object> row = List.of(i, 1, 2, 3);
                log.info("offset: {}, push = dense {}", size[1], row);
                context.pushDense(row);

                size[1] += 1;
            }
            log.info("offset: {}, push = done", size[1]);
        }

        Assertions.assertEquals(size[0], size[1]);
    }

    @Test
    public void testSparse() throws InterruptedException {
        int round = new Random().nextInt(10) + 1;
        log.info("test sparse, round: {}", round);

        final int[] size = {0, 0};

        DataAdaptor adaptor = new TestDataAdaptor() {
            @Override
            public void writeDataSparse(Data data, Iterator<List<KeyPair>> body) {
                Iterable<List<KeyPair>> iter = () -> body;
                for (List<KeyPair> row : iter) {
                    log.info("offset: {}, write = sparse {}", size[0], row);

                    size[0] += 1;
                }
                log.info("offset: {}, write = done", size[0]);
            }
        };

        try (PushContext context = PushContext.createSparse(adaptor, new Data())) {
            for (int i = 0; i < round; i++) {
                List<KeyPair> row = List.of(
                        KeyPair.of("id", i),
                        KeyPair.of("x1", 1),
                        KeyPair.of("x2", 2),
                        KeyPair.of("x3", 3)
                );
                log.info("offset: {}, push = sparse {}", size[1], row);
                context.pushSparse(row);

                size[1] += 1;
            }
            log.info("offset: {}, push = done", size[1]);
        }

        Assertions.assertEquals(size[0], size[1]);
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
    public InputStream readDataRaw(Data data) {
        return null;
    }

    @Override
    public Iterator<List<Object>> readDataDense(Data data) {
        return null;
    }

    @Override
    public Iterator<List<KeyPair>> readDataSparse(Data data) {
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

