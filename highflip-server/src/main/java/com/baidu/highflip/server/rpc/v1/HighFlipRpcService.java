package com.baidu.highflip.server.rpc.v1;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Config;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Operator;
import com.baidu.highflip.core.entity.runtime.Partner;
import com.baidu.highflip.core.entity.runtime.Platform;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Column;
import com.baidu.highflip.core.entity.runtime.basic.DataMode;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.baidu.highflip.core.entity.runtime.version.PlatformVersion;
import com.baidu.highflip.core.utils.ActionUtils;
import com.baidu.highflip.server.engine.HighFlipEngine;
import com.baidu.highflip.server.engine.dataio.PushContext;
import com.baidu.highflip.server.exception.HighFlipException;
import com.baidu.highflip.server.utils.PullProtoUtils;
import com.google.common.collect.Streams;
import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc.HighFlipImplBase;
import highflip.v1.Highflip;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    public Status handle(HighFlipException exc, GRpcExceptionScope scope) {
        return Status.ABORTED;
    }

    /******************************************************************************
     * CONFIGURATION
     ******************************************************************************/

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void listConfig(Highflip.ConfigListRequest request,
                           StreamObserver<Highflip.ConfigListResponse> responseObserver) {

        Iterator<Highflip.ConfigListResponse> response = Streams
                .stream(getEngine().getConfiguration().listKeys())
                .map(k -> Highflip.ConfigListResponse
                        .newBuilder()
                        .setKey(k)
                        .build())
                .iterator();

        returnMore(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void getConfig(Highflip.ConfigId request,
                          StreamObserver<Highflip.ConfigGetResponse> responseObserver) {

        Config entry = getEngine()
                .getConfiguration()
                .getEntry(request.getKey());

        Highflip.ConfigGetResponse response = Highflip.ConfigGetResponse
                .newBuilder()
                .setKey(entry.getKey())
                .setValue(entry.getValue())
                .setCreateTime(entry.getCreateTime().toString())
                .setUpdateTime(entry.getUpdateTime().toString())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void setConfig(Highflip.ConfigSetRequest request,
                          StreamObserver<Highflip.Void> responseObserver) {

        getEngine().getConfiguration()
                .setString(request.getKey(), request.getValue());

        returnVoid(responseObserver);
    }

    public void deleteConfig(Highflip.ConfigId request,
                             StreamObserver<Highflip.Void> responseObserver) {

        getEngine().getConfiguration()
                .delete(request.getKey());

        returnVoid(responseObserver);
    }

    /******************************************************************************
     * PLATFORM
     ******************************************************************************/

    /**
     * @param request
     * @param responseObserver
     */
    public void getPlatform(Highflip.Void request,
                            StreamObserver<Highflip.PlatformGetResponse> responseObserver) {

        Platform version = getEngine()
                .getPlatform();

        Highflip.PlatformGetResponse response = Highflip.PlatformGetResponse
                .newBuilder()
                .setVersion(version.toPlatformVersion().toProto())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void matchPlatform(Highflip.PlatformMatchRequest request,
                              StreamObserver<Highflip.PlatformMatchResponse> responseObserver) {

        PlatformVersion version = PlatformVersion
                .fromProto(request.getVersion());

        Platform platform = getEngine().matchPlatform(version);

        Highflip.PlatformMatchResponse response = Highflip.PlatformMatchResponse
                .newBuilder()
                .setVersion(platform.toPlatformVersion().toProto())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void createJob(Highflip.JobCreateRequest request,
                          StreamObserver<Highflip.JobId> responseObserver) {

        Job job = getEngine().createJob(
                request.getName(),
                request.getDescription(),
                Graph.fromProto(request.getDag()));

        Highflip.JobId response = toJobId(job.getJobId());

        returnOne(responseObserver, response);
    }

    /******************************************************************************
     * JOB
     ******************************************************************************/

    /**
     * @param request
     * @param responseObserver
     */
    public void getJob(Highflip.JobId request,
                       StreamObserver<Highflip.JobGetResponse> responseObserver) {
        Job job = getEngine().getJob(request.getJobId());

        Highflip.JobGetResponse response = Highflip.JobGetResponse
                .newBuilder()
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
     * @param request
     * @param responseObserver
     */
    public void checkJob(Highflip.JobId request,
                         StreamObserver<Highflip.JobCheckResponse> responseObserver) {

        Job job = getEngine().getJob(request.getJobId());

        Highflip.JobCheckResponse response = Highflip.JobCheckResponse
                .newBuilder()
                .setJobId(job.getJobId())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void deleteJob(Highflip.JobId request,
                          StreamObserver<Highflip.Void> responseObserver) {

        getEngine().deleteJob(request.getJobId());

        returnVoid(responseObserver);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void listJob(Highflip.JobListRequest request,
                        StreamObserver<Highflip.JobListResponse> responseObserver) {

        Iterator<String> ids = getEngine().listJobIds();

        Iterator<Highflip.JobListResponse> responses = Streams
                .stream(ids)
                .map(id -> Highflip.JobListResponse
                        .newBuilder()
                        .setJobId(id)
                        .build())
                .iterator();

        returnMore(responseObserver, responses);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void controlJob(Highflip.JobControlRequest request,
                           StreamObserver<Highflip.Void> responseObserver) {

        getEngine().controlJob(
                request.getJobId(),
                ActionUtils.fromProto(request.getAction()),
                request.getConfigurationMap());

        returnVoid(responseObserver);
    }

    public void getJobLog(Highflip.JobLogRequest request,
                          StreamObserver<Highflip.JobLogResponse> responseObserver) {

    }

    /******************************************************************************
     * TASK
     ******************************************************************************/
    /**
     * @param request
     * @param responseObserver
     */
    public void listTask(Highflip.TaskListRequest request,
                         StreamObserver<Highflip.TaskListResponse> responseObserver) {

        Iterator<Highflip.TaskListResponse> response = Streams
                .stream(getEngine().listTask(request.getJobId()))
                .map(t -> Highflip.TaskListResponse
                        .newBuilder()
                        .setTaskId(t.getTaskid())
                        .build())
                .iterator();

        returnMore(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void getTask(Highflip.TaskId request,
                        StreamObserver<Highflip.TaskGetResponse> responseObserver) {

        Task task = getEngine().getTask(request.getTaskId());

        Highflip.TaskGetResponse response = Highflip.TaskGetResponse
                .newBuilder()
                .setTaskId(task.getTaskid())
                .setJobId(task.getJobid())
                .setCreateTime(task.getCreateTime().toString())
                .setUpdateTime(task.getUpdateTime().toString())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void checkTask(Highflip.TaskId request,
                          StreamObserver<Highflip.TaskCheckResponse> responseObserver) {

        Task task = getEngine().getTask(request.getTaskId());

        Highflip.TaskCheckResponse response = Highflip.TaskCheckResponse
                .newBuilder()
                .setTaskId(task.getTaskid())
                .setJobId(task.getJobid())
                .setStatus(Highflip.TaskCheckResponse.TaskStatus.valueOf(task.getStatus()))
                .setMessage(task.getMessage())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void controlTask(Highflip.TaskControlRequest request,
                            StreamObserver<Highflip.Void> responseObserver) {

        getEngine().controlTask(
                request.getTaskId(),
                Action.valueOf(request.getAction().toString()),
                request.getConfiguration());

        returnVoid(responseObserver);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void getTaskLog(Highflip.TaskLogRequest request,
                           StreamObserver<Highflip.TaskLogResponse> responseObserver) {

    }

    /******************************************************************************
     * DATA
     ******************************************************************************/

    /**
     * @param request
     * @param responseObserver
     */
    public void listData(Highflip.DataListRequest request,
                         StreamObserver<Highflip.DataListResponse> responseObserver) {

        Iterator<Highflip.DataListResponse> itor = Streams
                .stream(getEngine().listData())
                .map(r -> Highflip.DataListResponse.newBuilder().setDataId(r).build())
                .iterator();

        returnMore(responseObserver, itor);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void getData(Highflip.DataId request,
                        StreamObserver<Highflip.DataGetResponse> responseObserver) {

        Data data = getEngine().getData(request.getDataId());

        HighflipMeta.DataProto schema = HighflipMeta.DataProto
                .newBuilder()
                .setName(data.getName())
                .setDescription(data.getDescription())
                .addAllColumns(data.getColumns()
                        .stream()
                        .map(com.baidu.highflip.core.entity.runtime.basic.Column::toProto)
                        .collect(Collectors.toList()))
                .build();

        Highflip.DataGetResponse response = Highflip.DataGetResponse
                .newBuilder()
                .setDataId(data.getDataId())
                .setSchema(schema)
                .setCreateTime(data.getCreateTime().toString())
                .setUpdateTime(data.getUpdateTime().toString())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param responseObserver
     * @return
     */
    public StreamObserver<Highflip.DataPushRequest> pushData(
            StreamObserver<Highflip.DataId> responseObserver) {

        return new StreamObserver<Highflip.DataPushRequest>() {

            PushContext context = null;

            Highflip.DataMode mode = null;

            void onHead(Highflip.DataPushRequest request) {
                Highflip.DataPushRequest.Head head = request.getHead();

                HighflipMeta.DataProto schema = head.getSchema();
                List<Column> columns = schema.getColumnsList()
                        .stream()
                        .map(Column::fromProto)
                        .collect(Collectors.toList());

                mode = head.getMode();

                context = getEngine().pushData(
                        schema.getName(),
                        schema.getDescription(),
                        DataMode.valueOf(mode.toString().toUpperCase()),
                        columns);
            }

            void onBody(Highflip.DataPushRequest request) throws InterruptedException {
                switch (mode) {
                    case DENSE:
                        for (Highflip.DenseData.Row row : request.getDense().getRowsList()) {
                            context.pushDense(new ArrayList<>(row.getValueList()));
                        }
                        break;
                    case SPARSE:
                        for (Highflip.SparseData.Row row : request.getSparse().getRowsList()) {
                            context.pushSparse(row.getPairsList()
                                    .stream()
                                    .map(pair -> new KeyPair(pair.getKey(), pair.getValue()))
                                    .collect(Collectors.toList()));
                        }
                        break;
                    case RAW:
                        context.pushRaw(request.getRaw()
                                .getContent()
                                .toByteArray());
                        break;
                }
            }

            @SneakyThrows
            @Override
            public void onNext(Highflip.DataPushRequest request) {
                if (context == null) {
                    onHead(request);
                } else {
                    onBody(request);
                }
            }

            @Override
            public void onError(Throwable t) {
                context.abord();
            }

            @Override
            public void onCompleted() {
                context.close();

                Highflip.DataId dataId = Highflip.DataId
                        .newBuilder()
                        .setDataId(context.getData().getDataId())
                        .build();

                returnOne(responseObserver, dataId);
            }
        };
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void pullData(Highflip.DataPullRequest request,
                         StreamObserver<Highflip.DataPullResponse> responseObserver) {

        switch (request.getMode()) {
            case RAW: {
                InputStream input = getEngine().pullDataRaw(
                        request.getDataId(),
                        request.getOffset(),
                        request.getLimit());

                returnMore(responseObserver,
                        PullProtoUtils.toRawResponse(input, request.getBatch()));
                break;
            }
            case DENSE: {
                Iterator<List<Object>> input = getEngine().pullDataDense(
                        request.getDataId(),
                        request.getOffset(),
                        request.getLimit());

                returnMore(responseObserver,
                        PullProtoUtils.toDenseResponse(input, request.getBatch()));
                break;
            }
            case SPARSE: {
                Iterator<List<KeyPair>> input = getEngine().pullDataSparse(
                        request.getDataId(),
                        request.getOffset(),
                        request.getLimit());

                returnMore(responseObserver,
                        PullProtoUtils.toSparseResponse(input, request.getBatch()));
                break;
            }
        }
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void deleteData(Highflip.DataId request,
                           StreamObserver<Highflip.Void> responseObserver) {

        getEngine().deleteData(request.getDataId());

        returnVoid(responseObserver);
    }

    /******************************************************************************
     * OPERATOR
     ******************************************************************************/
    /**
     * @param request
     * @param responseObserver
     */
    public void listOperator(Highflip.OperatorListRequest request,
                             StreamObserver<Highflip.OperatorListResponse> responseObserver) {
        Iterator<Highflip.OperatorListResponse> response = Streams
                .stream(getEngine().listOperator())
                .map(o -> Highflip.OperatorListResponse
                        .newBuilder()
                        .setOperatorId(o)
                        .build())
                .iterator();

        returnMore(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void getOperator(Highflip.OperatorId request,
                            StreamObserver<Highflip.OperatorGetResponse> responseObserver) {

        Operator oper = getEngine()
                .getOperator(request.getOperatorId());

        Highflip.OperatorGetResponse response = Highflip.OperatorGetResponse
                .newBuilder()
                .setOperatorId(oper.getOperatorId())
                .setSchema(HighflipMeta.OperatorProto
                        .newBuilder()
                        .setName(oper.getName())
                        .build())
                .build();

        returnOne(responseObserver, response);
    }

    /******************************************************************************
     * PARTNER
     ******************************************************************************/
    /**
     * @param request
     * @param responseObserver
     */
    public void createPartner(Highflip.PartnerCreateRequest request,
                              StreamObserver<Highflip.PartnerId> responseObserver) {

        String partnerId = getEngine()
                .createPartner(
                        request.getName(),
                        request.getDescription());

        Highflip.PartnerId response = Highflip.PartnerId
                .newBuilder()
                .setPartnerId(partnerId)
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void getPartner(Highflip.PartnerId request,
                           StreamObserver<Highflip.PartnerGetResponse> responseObserver) {

        Partner partner = getEngine()
                .getPartner(request.getPartnerId());

        Highflip.PartnerGetResponse response = Highflip.PartnerGetResponse
                .newBuilder()
                .setName(partner.getName())
                .setDescription(partner.getDescription())
                .setCreateTime(partner.getCreateTime().toString())
                .setUpdateTime(partner.getUpdateTime().toString())
                .build();

        returnOne(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void listPartner(Highflip.PartnerListRequest request,
                            StreamObserver<Highflip.PartnerListResponse> responseObserver) {

        Iterator<Highflip.PartnerListResponse> response = Streams
                .stream(getEngine().listPartner(0, 0))
                .map(p -> Highflip.PartnerListResponse
                        .newBuilder()
                        .setPartnerId(p)
                        .build())
                .iterator();

        returnMore(responseObserver, response);
    }

    /**
     * @param request
     * @param responseObserver
     */
    public void controlPartner(Highflip.PartnerControlRequest request,
                               StreamObserver<Highflip.Void> responseObserver) {

    }

}
