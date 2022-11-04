package com.baidu.highflip.client;

import com.baidu.highflip.utils.Streams;
import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
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

    /**
     * @return
     */
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

    public void setConfig(String key, String value) {
        Highflip.ConfigSetRequest request = Highflip.ConfigSetRequest
                .newBuilder()
                .setKey(key)
                .setValue(value)
                .build();

        getStub().setConfig(request);
    }


    /**
     * @return
     */
    public Highflip.PlatformGetResponse getPlatform() {

        Highflip.PlatformGetResponse response = getStub()
                .getPlatform(Highflip.Void.getDefaultInstance());

        return response;
    }

    /**
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

    /**
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
     * @param jobId
     */
    public void deleteJob(String jobId) {
        Highflip.JobId request = Highflip.JobId
                .newBuilder()
                .setJobId(jobId)
                .build();

        getStub().deleteJob(request);
    }

    /**
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

    public Iterable<String> listJob(int offset, int limit) {
        Highflip.JobListRequest request = Highflip.JobListRequest
                .newBuilder()
                .setOffset(offset)
                .setLimit(limit)
                .build();

        Iterator<Highflip.JobListResponse> response = getStub()
                .listJob(request);

        return () -> Streams.of(response)
                .map(Highflip.JobListResponse::getJobId)
                .iterator();
    }

    public Highflip.JobGetResponse getJob(String jobId) {
        Highflip.JobId request = Highflip.JobId
                .newBuilder()
                .setJobId(jobId)
                .build();

        Highflip.JobGetResponse response = getStub()
                .getJob(request);

        return response;
    }

    /**
     * @param offset
     * @param limit
     * @return
     */
    public Iterable<String> listData(int offset, int limit) {
        Highflip.DataListRequest request = Highflip.DataListRequest
                .newBuilder()
                .setOffset(offset)
                .setLimit(limit)
                .build();

        Iterator<Highflip.DataListResponse> response = getStub()
                .listData(request);

        return () -> Streams.of(response)
                .map(Highflip.DataListResponse::getDataId)
                .iterator();
    }

    public Highflip.DataGetResponse getData(String dataId) {
        Highflip.DataId request = Highflip.DataId
                .newBuilder()
                .setDataId(dataId)
                .build();

        Highflip.DataGetResponse response = getStub()
                .getData(request);
        return response;
    }

    public void deleteData(String dataId) {
        Highflip.DataId request = Highflip.DataId
                .newBuilder()
                .setDataId(dataId)
                .build();

        Highflip.Void response = getStub().deleteData(request);
    }

    public String pushDataRaw(String name, String description, InputStream body) {
        throw new UnsupportedOperationException();
    }

    public OutputStream pullDataRaw(String dataId) {
        throw new UnsupportedOperationException();
    }

    public Iterable<String> listOperators(int offset, int limit){
        Highflip.OperatorListRequest request = Highflip.OperatorListRequest
                .newBuilder()
                .setOffset(offset)
                .setLimit(limit)
                .build();

        Iterator<Highflip.OperatorListResponse> response = getStub()
                .listOperator(request);

        return () -> Streams.of(response)
                .map(Highflip.OperatorListResponse::getOperaterId)
                .iterator();
    }

    public Highflip.OperatorGetResponse getOperator(String operatorId){
        Highflip.OperatorId request = Highflip.OperatorId
                .newBuilder()
                .setOperaterId(operatorId)
                .build();

        Highflip.OperatorGetResponse response = getStub()
                .getOperator(request);

        return response;
    }

    /**
     *
     * @param offset
     * @param limit
     * @return
     */
    public Iterable<String> listPartners(int offset, int limit){
        Highflip.PartnerListRequest request = Highflip.PartnerListRequest
                .newBuilder()
                .setOffset(offset)
                .setLimit(limit)
                .build();

        Iterator<Highflip.PartnerListResponse> response = getStub()
                .listPartner(request);

        return () -> Streams.of(response)
                .map(Highflip.PartnerListResponse::getPartnerId)
                .iterator();
    }

    /**
     *
     * @param partnerId
     * @return
     */
    public Highflip.PartnerGetResponse getPartner(String partnerId){
        Highflip.PartnerId request = Highflip.PartnerId
                .newBuilder()
                .setPartnerId(partnerId)
                .build();

        Highflip.PartnerGetResponse response = getStub()
                .getPartner(request);

        return response;
    }

    /**
     *
     * @param name
     * @param description
     * @return
     */
    public String createPartner(String name, String description){
        Highflip.PartnerCreateRequest request = Highflip.PartnerCreateRequest
                .newBuilder()
                .setName(name)
                .setDescription(description)
                .build();

        Highflip.PartnerId response = getStub()
                .createPartner(request);

        return response.getPartnerId();
    }
}
