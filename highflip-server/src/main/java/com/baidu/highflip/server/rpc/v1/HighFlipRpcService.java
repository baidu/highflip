package com.baidu.highflip.server.rpc.v1;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.utils.ActionUtils;
import com.baidu.highflip.server.engine.HighFlipEngine;
import highflip.v1.HighFlipGrpc.HighFlipImplBase;
import highflip.v1.Highflip;
import highflip.v1.Highflip.JobCheckResponse;
import highflip.v1.Highflip.JobControlRequest;
import highflip.v1.Highflip.JobCreateRequest;
import highflip.v1.Highflip.JobGetResponse;
import highflip.v1.Highflip.JobId;
import highflip.v1.Highflip.JobListRequest;
import highflip.v1.Highflip.JobListResponse;
import highflip.v1.Highflip.PlatformGetResponse;
import highflip.v1.Highflip.PlatformMatchRequest;
import highflip.v1.Highflip.PlatformMatchResponse;
import highflip.v1.Highflip.PlatformVersion;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Streams;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

import static com.baidu.highflip.server.utils.GrpcServiceUtils.returnMore;
import static com.baidu.highflip.server.utils.GrpcServiceUtils.returnOne;
import static com.baidu.highflip.server.utils.GrpcServiceUtils.returnVoid;
import static com.baidu.highflip.server.utils.HighFlipUtils.toJobId;

@Slf4j
@GRpcService
public class HighFlipRpcService extends HighFlipImplBase {

    @Autowired
    HighFlipEngine engine;

    public HighFlipEngine getEngine() {
        return engine;
    }

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

        returnOne(responseObserver, get);
    }

    public void matchPlatform(PlatformMatchRequest request,
                              StreamObserver<PlatformMatchResponse> responseObserver) {

    }

    public void createJob(JobCreateRequest request,
                          StreamObserver<JobId> responseObserver) {

        Job job = getEngine().createJob(
                request.getName(),
                request.getDescription(),
                Graph.fromProto(request.getDag()));

        JobId response = toJobId(job.getJobId());

        returnOne(responseObserver, response);
    }

    /**
     *
     */
    public void getJob(JobId request,
                       StreamObserver<JobGetResponse> responseObserver) {
        Job job = getEngine().getJob(request.getJobId());

        JobGetResponse response = JobGetResponse.newBuilder()
                .setJobId(job.getJobId())
                .setName(job.getJobName())
                .setDescription(job.getDescription())
                .setCreateTime(job.getCreateTime().toString())
                .setUpdateTime(job.getUpdateTime().toString())
                .setFinishTime(job.getUpdateTime().toString())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     *
     */
    public void checkJob(JobId request,
                         StreamObserver<Highflip.JobCheckResponse> responseObserver) {

        Job job = getEngine().getJob(request.getJobId());

        JobCheckResponse response = JobCheckResponse.newBuilder()
                .setJobId(job.getJobId())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     *
     */
    public void deleteJob(JobId request,
                          StreamObserver<Highflip.Void> responseObserver) {

        getEngine().deleteJob(request.getJobId());

        returnVoid(responseObserver);
    }

    /**
     *
     */
    public void listJob(JobListRequest request,
                        StreamObserver<JobListResponse> responseObserver) {

        Iterator<String> ids = getEngine().listJobIds();

        Iterator<JobListResponse> responses = Streams.stream(ids)
                .map(id -> JobListResponse.newBuilder()
                        .setJobId(id)
                        .build()).iterator();

        returnMore(responseObserver, responses);
    }

    /**
     *
     */
    public void controlJob(JobControlRequest request,
                           StreamObserver<Highflip.Void> responseObserver) {

        getEngine().controlJob(
                request.getJobId(),
                ActionUtils.fromProto(request.getAction()));

        returnVoid(responseObserver);
    }


}
