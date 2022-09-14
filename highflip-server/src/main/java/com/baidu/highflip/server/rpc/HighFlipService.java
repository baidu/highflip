package com.baidu.highflip.server.rpc;

import com.baidu.highflip.server.engine.HighFlipEngine;
import io.grpc.stub.*;
import highflip.v1.*;
import highflip.v1.Highflip.*;
import highflip.v1.HighFlipGrpc.*;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GRpcService
public class HighFlipService extends HighFlipImplBase{

    @Autowired
    HighFlipEngine engine;

    public void getPlatform(Highflip.Void request,
                            StreamObserver<PlatformGetResponse> responseObserver) {
        log.info("get platform");

        PlatformVersion version = PlatformVersion.newBuilder()
                .setCompany("baidu")
                .setProduct("highflip")
                .setVersion("1.0.0")
                .build();
        PlatformGetResponse get = PlatformGetResponse.newBuilder()
                .setVersion(version)
                .build();
        responseObserver.onNext(get);
        responseObserver.onCompleted();
    }

    public void matchPlatform(PlatformMatchRequest request,
                              StreamObserver<PlatformMatchResponse> responseObserver) {

    }

    public void createJob(JobCreateRequest request,
                          io.grpc.stub.StreamObserver<JobId> responseObserver) {

        engine.createJob(null);
    }

    /**
     */
    public void getJob(JobId request,
                       io.grpc.stub.StreamObserver<JobGetResponse> responseObserver) {

    }

    /**
     */
    public void checkJob(JobId request,
                         io.grpc.stub.StreamObserver<Highflip.JobCheckResponse> responseObserver) {

    }

    /**
     */
    public void deleteJob(JobId request,
                          StreamObserver<Highflip.Void> responseObserver) {

    }

    /**
     */
    public void listJob(JobListRequest request,
                        StreamObserver<JobListResponse> responseObserver) {

    }

    /**
     */
    public void controlJob(JobControlRequest request,
                           StreamObserver<JobControlResponse> responseObserver) {

    }


}
