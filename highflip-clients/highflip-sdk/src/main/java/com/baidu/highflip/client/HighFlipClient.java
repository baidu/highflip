package com.baidu.highflip.client;

import com.baidu.highflip.utils.Streams;
import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
public class HighFlipClient implements AutoCloseable {

    public static final String HIGHFLIP_PROTO_SCHEMA = "grpc";

    public static final int HIGHFLIP_PORT_DEFAULT = 8751;

    ManagedChannel channel;

    HighFlipGrpc.HighFlipBlockingStub stub;

    public HighFlipClient() {
        this.channel = null;
        this.stub = null;
    }

    public void connect(String target) {
        close();

        HighFlipURL url = HighFlipURL.from(target);

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(url.getHost(), url.getPort())
                .usePlaintext()
                .build();

        HighFlipGrpc.HighFlipBlockingStub stub = HighFlipGrpc.newBlockingStub(channel);

        this.channel = channel;
        this.stub = stub;
    }

    @Override
    public void close() {
        if (channel != null) {
            channel.shutdownNow();
        }

        this.stub = null;
        this.channel = null;
    }

    public boolean isConnected() {
        if (channel == null) {
            return false;
        }

        if (channel.isTerminated()) {
            return false;
        }

        return true;
    }

    protected HighFlipGrpc.HighFlipBlockingStub getStub() {
        return this.stub;
    }

    /**
     *
     * @return
     */
    public Highflip.PlatformGetResponse getPlatform() {

        Highflip.PlatformGetResponse response = getStub()
                .getPlatform(Highflip.Void.getDefaultInstance());

        return response;
    }

    /*
     * @param name
     * @param description
     * @param dag
     * @return
     */
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

    /*
     */
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

    public Iterator<String> listJob() {
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
