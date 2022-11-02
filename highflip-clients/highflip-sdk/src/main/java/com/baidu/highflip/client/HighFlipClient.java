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

    public static final Integer HIGHFLIP_PORT_DEFAULT = 8751;

    public static final String HIGHFLIP_ADDRESS_DEFAULT = "127.0.0.1";

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

    public Iterable<String> listConfig() {
        Highflip.ConfigListRequest request = Highflip.ConfigListRequest
                .newBuilder()
                .build();

        Iterator<Highflip.ConfigListResponse> response = getStub()
                .listConfig(Highflip.ConfigListRequest
                        .newBuilder()
                        .build());

        return () -> Streams.of(response)
                .map(r -> r.getKey())
                .iterator();
    }


    public Highflip.ConfigGetResponse getConfig(String key) {
        Highflip.ConfigGetRequest request = Highflip.ConfigGetRequest
                .newBuilder()
                .setKey(key)
                .build();

        Highflip.ConfigGetResponse response = getStub()
                .getConfig(request);

        return response;
    }

    public  void setConfig(String key, String value) {
        Highflip.ConfigSetRequest request = Highflip.ConfigSetRequest
                .newBuilder()
                .setKey(key)
                .setValue(value)
                .build();

        getStub().setConfig(request);
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

    /**
     *
     * @param company
     * @param product
     * @param version
     */
    public void matchPlatform(String company, String product, String version) {
        Highflip.PlatformVersion platver = Highflip.PlatformVersion
                .newBuilder()
                .setCompany(company)
                .setProduct(product)
                .setVersion(version)
                .build();

        Highflip.PlatformMatchRequest request = Highflip.PlatformMatchRequest
                .newBuilder()
                .setVersion(platver)
                .build();

        Iterator<Highflip.PlatformMatchResponse> response = getStub()
                .matchPlatform(request);
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

    /**
     *
     * @param jobId
     * @param action
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
