package com.baidu.highflip.client;

import com.baidu.highflip.utils.Streams;
import com.google.common.collect.Iterables;
import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Slf4j
public class HighFlipClient implements AutoCloseable {

    ManagedChannel channel;

    HighFlipGrpc.HighFlipBlockingStub stub;

    protected HighFlipClient(ManagedChannel channel, HighFlipGrpc.HighFlipBlockingStub stub) {
        this.channel = channel;
        this.stub = stub;
    }

    public HighFlipClient(){

    }

    public static HighFlipClient connect(String target) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();

        HighFlipGrpc.HighFlipBlockingStub stub = HighFlipGrpc.newBlockingStub(channel);
        return new HighFlipClient(channel, stub);
    }

    @Override
    public void close() {
        channel.shutdownNow();
    }

    protected HighFlipGrpc.HighFlipBlockingStub getStub() {
        return this.stub;
    }

    public String createJob(String name, String description, HighflipMeta.GraphProto dag) {
        Highflip.JobCreateRequest request = Highflip.JobCreateRequest
                .newBuilder()
                .setName(name)
                .setDescription(description)
                .setDag(dag)
                .build();

        Highflip.JobId response = getStub().createJob(request);
        return response.getJobId();
    }

    public void controlJob(String jobId, String action) {
        Highflip.JobControlRequest request = Highflip.JobControlRequest
                .newBuilder()
                .setJobId(jobId)
                .setAction(Highflip.JobControlRequest.Action.valueOf(action))
                .build();

        Highflip.Void response = getStub().controlJob(request);
    }

    public String checkJob(String jobId) {
        Highflip.JobId request = Highflip.JobId
                .newBuilder()
                .setJobId(jobId)
                .build();

        Highflip.JobCheckResponse response = getStub().checkJob(request);
        return response.getStatus().toString();
    }

    public Iterator<String> listJob(){
        Highflip.JobListRequest request = Highflip.JobListRequest
                .newBuilder()
                .build();

        Iterator<Highflip.JobListResponse> response = getStub()
                .listJob(request);

        return Streams.of(response)
                .map(Highflip.JobListResponse::getJobId)
                .iterator();
    }
}
