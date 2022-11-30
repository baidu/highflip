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
    public synchronized void onCompleted() {
        notify();
    }

    public synchronized T getOrThrow() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(exp != null){
            throw new RuntimeException(exp);
        }
        return value;
    }
}
