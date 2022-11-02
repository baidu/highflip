package com.baidu.highflip.server.utils;

import highflip.v1.Highflip;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

public class GrpcServiceUtils {

    public static void returnVoid(StreamObserver<Highflip.Void> responseObserver) {
        Highflip.Void response = Highflip.Void
                .newBuilder()
                .build();

        returnOne(responseObserver, response);
    }

    public static <T> void returnOne(StreamObserver<T> responseObserver, T result) {
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    public static <T> void returnMore(StreamObserver<T> responseObserver, Iterator<T> iterator) {
        iterator.forEachRemaining(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
