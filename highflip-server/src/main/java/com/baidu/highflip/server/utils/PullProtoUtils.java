package com.baidu.highflip.server.utils;

import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.google.common.collect.Streams;
import com.google.protobuf.ByteString;
import highflip.v1.Highflip;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PullProtoUtils {

    public static <T> Iterator<List<T>> toBatch(Iterator<T> inputs, int batch) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return inputs.hasNext();
            }

            @Override
            public List<T> next() {
                List<T> next = new ArrayList<>(batch);
                for (int i = 0; i < batch && inputs.hasNext(); i++) {
                    next.add(inputs.next());
                }
                return next;
            }
        };
    }

    public static Iterator<byte[]> toBatch(InputStream input, int batch) {
        return new InputStreamIterator(input, batch);
    }

    public static Highflip.DenseData.Row toDenseRow(List<Object> row) {
        return Highflip.DenseData.Row
                .newBuilder()
                .addAllValue(row.stream()
                        .map(Object::toString)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Highflip.SparseData.Row toSparseRow(List<KeyPair> row) {
        return Highflip.SparseData.Row
                .newBuilder()
                .addAllPairs(row.stream()
                        .map(kv -> Highflip.SparseData.Pair
                                .newBuilder()
                                .setKey(kv.getKey())
                                .setValue(kv.getValue().toString())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static Iterator<Highflip.DataPullResponse> toRawResponse(InputStream input, int batch) {
        return Streams.stream(toBatch(input, batch))
                .map(list -> Highflip.DataPullResponse
                        .newBuilder()
                        .setRaw(Highflip.RawData
                                .newBuilder()
                                .setContent(ByteString.copyFrom(list))
                                .build())
                        .build())
                .iterator();
    }

    public static Iterator<Highflip.DataPullResponse> toDenseResponse(Iterator<List<Object>> rows, int batch) {
        return Streams.stream(toBatch(rows, batch))
                .map(list -> Highflip.DataPullResponse
                        .newBuilder()
                        .setDense(Highflip.DenseData
                                .newBuilder()
                                .addAllRows(list.stream()
                                        .map(PullProtoUtils::toDenseRow)
                                        .collect(Collectors.toList()))
                                .build())
                        .build())
                .iterator();
    }

    public static Iterator<Highflip.DataPullResponse> toSparseResponse(Iterator<List<KeyPair>> rows, int batch) {
        return Streams.stream(toBatch(rows, batch))
                .map(list -> Highflip.DataPullResponse
                        .newBuilder()
                        .setSparse(Highflip.SparseData
                                .newBuilder()
                                .addAllRows(list.stream()
                                        .map(PullProtoUtils::toSparseRow)
                                        .collect(Collectors.toList()))
                                .build())
                        .build())
                .iterator();
    }

    static class InputStreamIterator implements Iterator<byte[]> {

        BufferedInputStream input;

        int batch;

        byte[] next = null;

        public InputStreamIterator(InputStream input, int batch) {
            this.input = new BufferedInputStream(input);
            this.batch = batch;
            this.next = load();
        }

        public byte[] load() {
            try {
                return input.readNBytes(batch);
            } catch (IOException e) {
                return null;
            }
        }


        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public byte[] next() {
            byte[] rest = next;
            next = load();
            return rest;
        }
    }
}

