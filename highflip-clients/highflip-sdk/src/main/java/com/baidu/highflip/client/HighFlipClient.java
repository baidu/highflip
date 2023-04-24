package com.baidu.highflip.client;

import com.baidu.highflip.client.common.BasicToken;
import com.baidu.highflip.client.common.GrpcURL;
import com.baidu.highflip.client.stream.DataPullStream;
import com.baidu.highflip.client.stream.DataPushStream;
import com.baidu.highflip.client.common.OneObserver;
import com.baidu.highflip.client.model.KeyPair;
import com.baidu.highflip.client.model.Schema;
import com.baidu.highflip.client.utils.Streams;
import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
import io.grpc.CallCredentials;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class HighFlipClient implements AutoCloseable {

    public static final String HIGHFLIP_PROTO_SCHEMA = "grpc";

    public static final Integer HIGHFLIP_PORT_DEFAULT = 8751;

    public static final String HIGHFLIP_ADDRESS_DEFAULT = "127.0.0.1";

    ManagedChannel channel;

    HighFlipGrpc.HighFlipBlockingStub blockingStub;

    HighFlipGrpc.HighFlipStub stub;

    public HighFlipClient() {
        this.channel = null;
        this.stub = null;
        this.blockingStub = null;
    }

    public static HighFlipClient newHighFlipClient(String target) {
        HighFlipClient highFlipClient = new HighFlipClient();
        highFlipClient.connect(target);
        return highFlipClient;
    }

    public void connect(String target) {
        close();

        GrpcURL url = GrpcURL.from(target);

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(url.getHost(), url.getPort())
                .usePlaintext()
                .build();

        HighFlipGrpc.HighFlipBlockingStub blockingStub = HighFlipGrpc.newBlockingStub(channel);
        HighFlipGrpc.HighFlipStub stub = HighFlipGrpc.newStub(channel);

        Optional<BasicToken> token = BasicToken.of(
                url.getUser(), url.getPass());
        if(token.isPresent()){
            blockingStub = blockingStub.withCallCredentials(token.get());
            stub = stub.withCallCredentials(token.get());
        }

        this.channel = channel;
        this.blockingStub = blockingStub;
        this.stub = stub;
    }

    @Override
    public void close() {
        if (channel != null) {
            channel.shutdownNow();
        }

        this.stub = null;
        this.blockingStub = null;
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

    protected HighFlipGrpc.HighFlipBlockingStub getBlockingStub() {
        return this.blockingStub;
    }

    protected HighFlipGrpc.HighFlipStub getStub() {
        return this.stub;
    }

    /**
     * @return
     */
    public Iterable<String> listConfig() {
        Highflip.ConfigListRequest request = Highflip.ConfigListRequest
                .newBuilder()
                .build();

        Iterator<Highflip.ConfigListResponse> response = getBlockingStub()
                .listConfig(Highflip.ConfigListRequest
                        .newBuilder()
                        .build());

        return () -> Streams.of(response)
                .map(r -> r.getKey())
                .iterator();
    }


    public Highflip.ConfigGetResponse getConfig(String key) {
        Highflip.ConfigId request = Highflip.ConfigId
                .newBuilder()
                .setKey(key)
                .build();

        Highflip.ConfigGetResponse response = getBlockingStub()
                .getConfig(request);

        return response;
    }

    public void setConfig(String key, String value) {
        Highflip.ConfigSetRequest request = Highflip.ConfigSetRequest
                .newBuilder()
                .setKey(key)
                .setValue(value)
                .build();

        getBlockingStub().setConfig(request);
    }

    public void deleteConfig(String key) {
        Highflip.ConfigId request = Highflip.ConfigId
                .newBuilder()
                .setKey(key)
                .build();

        Highflip.Void response = getBlockingStub()
                .deleteConfig(request);
    }

    /**
     * @return
     */
    public Highflip.PlatformGetResponse getPlatform() {

        Highflip.PlatformGetResponse response = getBlockingStub()
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

        Iterator<Highflip.PlatformMatchResponse> response = getBlockingStub()
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

        Highflip.JobId response = getBlockingStub().createJob(request);
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

        getBlockingStub().deleteJob(request);
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

        Highflip.Void response = getBlockingStub().controlJob(request);
    }

    public String checkJob(String jobId) {
        Highflip.JobId request = Highflip.JobId
                .newBuilder()
                .setJobId(jobId)
                .build();

        Highflip.JobCheckResponse response = getBlockingStub().checkJob(request);
        return response.getStatus().toString();
    }

    public Iterable<String> listJob(int offset, int limit) {
        Highflip.JobListRequest request = Highflip.JobListRequest
                .newBuilder()
                .setOffset(offset)
                .setLimit(limit)
                .build();

        Iterator<Highflip.JobListResponse> response = getBlockingStub()
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

        Highflip.JobGetResponse response = getBlockingStub()
                .getJob(request);

        return response;
    }

    /**
     *
     * @param jobId
     * @return
     */
    public Iterable<String> getJobLog(String jobId){
        Highflip.JobLogRequest request = Highflip.JobLogRequest
                .newBuilder()
                .setJobId(jobId)
                .build();

        Iterator<Highflip.JobLogResponse> response = getBlockingStub()
                .getJobLog(request);

        return () -> Streams.of(response)
                .flatMap(r -> r.getLinesList().stream())
                .iterator();
    }

    /**
     *
     * @param offset
     * @param limit
     * @return
     */
    public Iterable<String> listTasks(int offset, int limit){
        Highflip.TaskListRequest request = Highflip.TaskListRequest
                .newBuilder()
                .build();

        Iterator<Highflip.TaskListResponse> response = getBlockingStub()
                .listTask(request);

        return () -> Streams.of(response)
                .map(Highflip.TaskListResponse::getTaskId)
                .iterator();
    }

    /**
     *
     * @param taskId
     * @return
     */
    public Highflip.TaskGetResponse getTask(String taskId){
        Highflip.TaskId request = Highflip.TaskId
                .newBuilder()
                .setTaskId(taskId)
                .build();

        Highflip.TaskGetResponse response = getBlockingStub()
                .getTask(request);

        return response;
    }

    /**
     *
     * @param taskId
     * @return
     */
    public String checkTask(String taskId){
        Highflip.TaskId request = Highflip.TaskId
                .newBuilder()
                .setTaskId(taskId)
                .build();

        Highflip.TaskCheckResponse response = getBlockingStub()
                .checkTask(request);

        return response.getStatus().toString();
    }

    /**
     *
     * @param taskId
     * @param action
     */
    public void controlTask(String taskId, String action){
        Highflip.TaskControlRequest request = Highflip.TaskControlRequest
                .newBuilder()
                .setTaskId(taskId)
                .setAction(Highflip.TaskControlRequest.Action.valueOf(action))
                .build();

        Highflip.Void response = getBlockingStub()
                .controlTask(request);
    }

    /**
     *
     * @param taskId
     * @return
     */
    public Iterable<String> getTaskLog(String taskId){
        Highflip.TaskLogRequest request = Highflip.TaskLogRequest
                .newBuilder()
                .setTaskId(taskId)
                .build();

        Iterator<Highflip.TaskLogResponse> response = getBlockingStub()
                .getTaskLog(request);

        return () -> Streams.of(response)
                .flatMap(r -> r.getLinesList().stream())
                .iterator();
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

        Iterator<Highflip.DataListResponse> response = getBlockingStub()
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

        Highflip.DataGetResponse response = getBlockingStub()
                .getData(request);
        return response;
    }

    public void deleteData(String dataId) {
        Highflip.DataId request = Highflip.DataId
                .newBuilder()
                .setDataId(dataId)
                .build();

        Highflip.Void response = getBlockingStub()
                .deleteData(request);
    }

    public String pushDataRaw(Schema schema, InputStream body, int batch) {
        OneObserver<Highflip.DataId> response = new OneObserver<>();

        DataPushStream stream = DataPushStream.of(getStub().pushData(response));
        stream.pushHead("RAW", schema);
        stream.pushRaw(body, batch);

        return response.getOrThrow().getDataId();
    }

    public String pushDataDense(Schema schema, Iterator<List<String>> body, int batch) {
        OneObserver<Highflip.DataId> response = new OneObserver<>();

        DataPushStream stream = DataPushStream.of(getStub().pushData(response));
        stream.pushHead("DENSE", schema);
        stream.pushDense(body, batch);

        return response.getOrThrow().getDataId();
    }

    public String pushDataSparse(Schema schema, Iterator<List<KeyPair>> body, int batch) {
        OneObserver<Highflip.DataId> response = new OneObserver<>();

        DataPushStream stream = DataPushStream.of(getStub().pushData(response));
        stream.pushHead("SPARSE", schema);
        stream.pushSparse(body, batch);

        return response.getOrThrow().getDataId();
    }

    public InputStream pullDataRaw(String dataId, int batch) {
        Highflip.DataPullRequest request = Highflip.DataPullRequest
                .newBuilder()
                .setDataId(dataId)
                .setMode(Highflip.DataMode.RAW)
                .setBatch(batch)
                .build();

        Iterator<Highflip.DataPullResponse> response = getBlockingStub()
                .pullData(request);

        return DataPullStream.toRaw(response);
    }

    public Iterator<List<String>> pullDataDense(String dataId, int batch) {
        Highflip.DataPullRequest request = Highflip.DataPullRequest
                .newBuilder()
                .setDataId(dataId)
                .setMode(Highflip.DataMode.DENSE)
                .setBatch(batch)
                .build();

        Iterator<Highflip.DataPullResponse> response = getBlockingStub()
                .pullData(request);

        return DataPullStream.toDense(response);
    }

    public Iterator<List<KeyPair>> pullDataSparse(String dataId, int batch) {
        Highflip.DataPullRequest request = Highflip.DataPullRequest
                .newBuilder()
                .setDataId(dataId)
                .setMode(Highflip.DataMode.SPARSE)
                .setBatch(batch)
                .build();

        Iterator<Highflip.DataPullResponse> response = getBlockingStub()
                .pullData(request);

        return DataPullStream.toSparse(response);
    }

    public Iterator<List<Object>> pullDataObject(String dataId) {
        Highflip.DataPullRequest request = Highflip.DataPullRequest
                .newBuilder()
                .setDataId(dataId)
                .build();

        Iterator<Highflip.DataPullResponse> response = getBlockingStub()
                .pullData(request);

        return DataPullStream.toObjects(response);
    }


    public Iterable<String> listOperators(int offset, int limit){
        Highflip.OperatorListRequest request = Highflip.OperatorListRequest
                .newBuilder()
                .setOffset(offset)
                .setLimit(limit)
                .build();

        Iterator<Highflip.OperatorListResponse> response = getBlockingStub()
                .listOperator(request);

        return () -> Streams.of(response)
                .map(Highflip.OperatorListResponse::getOperatorId)
                .iterator();
    }

    public Highflip.OperatorGetResponse getOperator(String operatorId){
        Highflip.OperatorId request = Highflip.OperatorId
                .newBuilder()
                .setOperatorId(operatorId)
                .build();

        Highflip.OperatorGetResponse response = getBlockingStub()
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

        Iterator<Highflip.PartnerListResponse> response = getBlockingStub()
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

        Highflip.PartnerGetResponse response = getBlockingStub()
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

        Highflip.PartnerId response = getBlockingStub()
                .createPartner(request);

        return response.getPartnerId();
    }
}
