package com.baidu.highflip.server.engine.dataio;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PushContext implements Closeable {

    volatile boolean isFinished = false;

    LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    DataAdaptor adaptor;

    Thread thread;

    Data data;

    Exception exception = null;

    protected PushContext() {

    }

    public static PushContext createDense(DataAdaptor adaptor, Data data) {
        PushContext context = new PushContext();
        context.data = data;
        context.adaptor = adaptor;


        context.thread = new Thread(new ContextWriter(context) {
            @Override
            public void run() {
                try {
                    context.writeDense();
                } catch (Exception e) {
                    context.exception = e;
                    log.error("write dense error", e);
                }
            }
        });
        context.thread.start();
        return context;
    }

    public static PushContext createSparse(DataAdaptor adaptor, Data data) {
        PushContext context = new PushContext();
        context.data = data;
        context.adaptor = adaptor;
        context.thread = new Thread(new ContextWriter(context) {
            @Override
            public void run() {
                try {
                    context.writeSparse();
                } catch (Exception e) {
                    context.exception = e;
                    log.error("write sparse error", e);
                }
            }
        });
        context.thread.start();
        return context;
    }

    public static PushContext createRaw(DataAdaptor adaptor, Data data) {
        PushContext context = new PushContext();
        context.data = data;
        context.adaptor = adaptor;
        context.thread = new Thread(new ContextWriter(context) {
            @Override
            public void run() {
                try {
                    context.writeRaw();
                } catch (Exception e) {
                    context.exception = e;
                    log.error("write raw error", e);
                }
            }
        });
        context.thread.start();
        return context;
    }

    void writeDense() {
        adaptor.writeDataDense(data,
                new QueueIterator<>());
    }

    void writeSparse() {
        adaptor.writeDataSparse(data,
                new QueueIterator<>());
    }

    void writeRaw() {
        adaptor.writeDataRaw(data,
                new QueueInputStream());
    }

    public Data getData() {
        return data;
    }

    public boolean isDone() {
        return thread.isAlive();
    }

    public void abord() {

    }

    public void close() {
        try {
            isFinished = true;
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void pushRaw(byte[] row) throws InterruptedException {
        queue.put(row);
    }

    public void pushDense(List<Object> row) throws InterruptedException {
        queue.put(row);
    }

    public void pushSparse(List<KeyPair> row) throws InterruptedException {
        queue.put(row);
    }

    static abstract class ContextWriter implements Runnable {

        PushContext context;

        ContextWriter(PushContext context) {
            this.context = context;
        }
    }

    class QueueIterator<T> implements Iterator<T> {

        int timeoutSec = 1;

        @Override
        public boolean hasNext() {
            return !isFinished || !queue.isEmpty();
        }

        @Override
        public T next() {
            try {
                while (true) {
                    T value = (T) queue.poll(timeoutSec, TimeUnit.SECONDS);
                    if (value == null) {
                        if (isFinished) {
                            throw new NoSuchElementException();
                        }
                    } else {
                        return value;
                    }
                }
            } catch (InterruptedException e) {
                throw new NoSuchElementException();
            }
        }
    }

    class QueueInputStream extends InputStream {

        QueueIterator<byte[]> iterator = new QueueIterator<>();

        int index = 0;

        byte[] bytes = null;

        boolean hasNext() {
            return bytes != null || iterator.hasNext();
        }

        byte next() {
            if (bytes == null || index >= bytes.length) {
                bytes = iterator.next();
                index = 0;
            }
            return bytes[index++];
        }

        @Override
        public int read() throws IOException {
            try {
                if (!hasNext()) {
                    return -1;
                } else {
                    return Byte.toUnsignedInt(next());
                }
            } catch (NoSuchElementException e) {
                return -1;
            }
        }
    }
}
