package com.baidu.highflip.client.common;

import io.grpc.stub.StreamObserver;

public class OneObserver<T> implements StreamObserver<T>{

    T value = null;

    Throwable exp = null;

    @Override
    public void onNext(T value) {
        this.value = value;
    }

    @Override
    public void onError(Throwable t) {
        this.exp = t;
    }

    @Override
    public void onCompleted() {

    }

    public T getOrThrow() {
        if(exp != null){
            throw new RuntimeException(exp);
        }
        return value;
    }
}
