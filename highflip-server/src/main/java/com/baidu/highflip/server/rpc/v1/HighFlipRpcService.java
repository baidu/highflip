package com.baidu.highflip.server.rpc.v1;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Platform;
import com.baidu.highflip.core.entity.runtime.version.PlatformVersion;
import com.baidu.highflip.core.utils.ActionUtils;
import com.baidu.highflip.server.engine.HighFlipEngine;
import com.baidu.highflip.server.exception.HighFlipEngineException;
import com.google.common.collect.Streams;
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
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.lognet.springboot.grpc.recovery.GRpcExceptionHandler;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
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

    // @GRpcExceptionHandler
    public Status handle (HighFlipEngineException exc, GRpcExceptionScope scope){
        return Status.ABORTED;
    }

    public void getPlatform(Highflip.Void request,
                            StreamObserver<PlatformGetResponse> responseObserver) {

        Platform version = getEngine()
                .getPlatform();

        PlatformGetResponse response = PlatformGetResponse.newBuilder()
                .setVersion(version.toPlatformVersion().toProto())
                .build();

        returnOne(responseObserver, response);
    }

    public void matchPlatform(PlatformMatchRequest request,
                              StreamObserver<PlatformMatchResponse> responseObserver) {

        PlatformVersion version = PlatformVersion
                .fromProto(request.getVersion());

        Platform platform = getEngine().matchPlatform(version);

        PlatformMatchResponse response = PlatformMatchResponse.newBuilder()
                .setVersion(platform.toPlatformVersion().toProto())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     *
     * @param request
     * @param responseObserver
     */
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
