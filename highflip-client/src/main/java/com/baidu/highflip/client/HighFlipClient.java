package com.baidu.highflip.client;

import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HighFlipClient {
    ManagedChannel channel;
    HighFlipGrpc.HighFlipBlockingStub stub;

    public void connect(String target){
        this.channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();

        this.stub = HighFlipGrpc.newBlockingStub(channel);
    }

    protected HighFlipGrpc.HighFlipBlockingStub getStub(){
        return this.stub;
    }

    public String createJob(String name, String description, HighflipMeta.GraphProto dag){
        Highflip.JobCreateRequest request =  Highflip.JobCreateRequest
                .newBuilder()
                .setName(name)
                .setDescription(description)
                .setDag(dag)
                .build();

        Highflip.JobId response = getStub().createJob(request);
        return response.getJobId();
    }

    public void controlJob(String jobId, String action){
        Highflip.JobControlRequest request = Highflip.JobControlRequest
                .newBuilder()
                .setJobId(jobId)
                .setAction(Highflip.JobControlRequest.Action.valueOf(action))
                .build();

        Highflip.Void response = getStub().controlJob(request);
    }

    public String checkJob(String jobId){
        Highflip.JobId request = Highflip
                .JobId.newBuilder()
                .setJobId(jobId)
                .build();

        Highflip.JobCheckResponse response = getStub().checkJob(request);
        return response.getStatus().toString();
    }
}
