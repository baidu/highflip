package com.baidu.highflip.client.dataio;

import com.baidu.highflip.client.model.KeyPair;
import com.baidu.highflip.client.utils.Streams;
import com.google.protobuf.ByteString;
import highflip.v1.Highflip;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DataPushStream{

    StreamObserver<Highflip.DataPushRequest> streamObserver;

    public static DataPushStream of(StreamObserver<Highflip.DataPushRequest> streamObserver){
        return new DataPushStream(streamObserver);
    }

    public void pushHead(
        String name,
        String description){

        Highflip.DataPushRequest.Head head = Highflip.DataPushRequest.Head
                .newBuilder()
                .build();

        Highflip.DataPushRequest request = Highflip.DataPushRequest
                .newBuilder()
                .setHead(head)
                .build();

        streamObserver.onNext(request);
    }

    public void pushRaw(
        InputStream input, int batch){

        byte[] buffer = new byte[batch];

        try {
            while (true){
                int done = input.read(buffer);
                if(done > 0) {
                    Highflip.DataPushRequest request = Highflip.DataPushRequest
                            .newBuilder()
                            .setRaw(Highflip.RawData
                                    .newBuilder()
                                    .setContent(ByteString.copyFrom(buffer, 0, done))
                                    .build())
                            .build();

                    streamObserver.onNext(request);
                } else {
                    streamObserver.onCompleted();
                    break;
                }
            }
        } catch (IOException e) {
            streamObserver.onError(e);
        }
    }

    static Highflip.DenseData toDenseData(Iterable<List<String>> rows){

        Highflip.DenseData.Builder builder = Highflip.DenseData
                .newBuilder();

        for(List<String> row: rows){
            builder.addRows(Highflip.DenseData.Row
                    .newBuilder()
                    .addAllValue(row)
                    .build());
        }

        return builder.build();
    }

    public void pushDense(
        Iterator<List<String>> input,
        int batch){

        Streams.toBatch(input, 10).forEachRemaining(rows -> {
            Highflip.DataPushRequest request = Highflip.DataPushRequest
                    .newBuilder()
                    .setDense(toDenseData(rows))
                    .build();

            streamObserver.onNext(request);
        });
        streamObserver.onCompleted();
    }

    static Highflip.SparseData toSparseData(Iterable<List<KeyPair>> rows){

        Highflip.SparseData.Builder builder = Highflip.SparseData
                .newBuilder();

        for(List<KeyPair> row: rows){
            Iterable<Highflip.SparseData.Pair> r = row.stream()
                    .map(kv -> Highflip.SparseData.Pair
                        .newBuilder()
                        .setKey(kv.getKey())
                        .setValue(kv.getValue())
                        .build())
                    .collect(Collectors.toList());

            builder.addRows(Highflip.SparseData.Row
                    .newBuilder()
                    .addAllPairs(r)
                    .build());
        }

        return builder.build();
    }

    public void pushSparse(
        Iterator<List<KeyPair>> input,
        int batch){

        Streams.toBatch(input, 10).forEachRemaining(rows -> {
            Highflip.DataPushRequest request = Highflip.DataPushRequest
                    .newBuilder()
                    .setSparse(toSparseData(rows))
                    .build();

            streamObserver.onNext(request);
        });
        streamObserver.onCompleted();
    }
}
