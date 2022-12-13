package com.baidu.highflip.server;

import com.baidu.highflip.server.common.GrpcServerTestBase;
import com.google.common.collect.Streams;
import highflip.HighflipMeta;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@ActiveProfiles({"disable-security"})
public class TestHighflipGrpServer extends GrpcServerTestBase {

    HighFlipGrpc.HighFlipBlockingStub getStub() {
        return HighFlipGrpc.newBlockingStub(getChannel());
    }

    HighFlipGrpc.HighFlipStub getRawStub() {
        return HighFlipGrpc.newStub(getChannel());
    }

    @Test
    public void testGetPlatform() {

        Highflip.PlatformGetResponse response = getStub()
                .getPlatform(Highflip.Void.getDefaultInstance());

        log.info("get platform: {}", response);
    }

    @Test
    public void testListConfig() {

        Highflip.ConfigListRequest request = Highflip.ConfigListRequest
                .newBuilder()
                .build();

        List<String> result = Streams
                .stream(getStub().listConfig(request))
                .map(Highflip.ConfigListResponse::getKey)
                .collect(Collectors.toList());

        for (String name : result) {
            log.info("config name: {}", name);
        }
    }

    @Test
    public void testCreateJob() {
        HighflipMeta.GraphProto graph = HighflipMeta.GraphProto
                .newBuilder()
                .setName("test_graph")
                .build();

        Highflip.JobCreateRequest request = Highflip.JobCreateRequest
                .newBuilder()
                .setName("test")
                .setDag(graph)
                .build();

        highflip.v1.Highflip.JobId response = getStub()
                .createJob(request);

        log.info("create job id: {}", response.getJobId());
    }

    @Test
    public void testListJob() {

        Highflip.JobListRequest request = Highflip.JobListRequest
                .newBuilder()
                .build();

        List<String> result = Streams
                .stream(getStub().listJob(request))
                .map(Highflip.JobListResponse::getJobId)
                .collect(Collectors.toList());

        for (String jobId : result) {
            log.info("job id: {}", jobId);
        }
    }

    @Test
    public void testListTask() {

        Highflip.TaskListRequest request = Highflip.TaskListRequest
                .newBuilder()
                .build();

        List<String> result = Streams
                .stream(getStub().listTask(request))
                .map(Highflip.TaskListResponse::getTaskId)
                .collect(Collectors.toList());

        for (String taskId : result) {
            log.info("task id: {}", taskId);
        }
    }

    @Test
    public void testPushData() {

        StreamObserver<Highflip.DataPushRequest> requests = getRawStub()
                .pushData(new StreamObserver<>() {

                    @Override
                    public void onNext(Highflip.DataId value) {
                        log.info("push data id: {}", value.getDataId());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                });

        Highflip.DataPushRequest head = Highflip.DataPushRequest
                .newBuilder()
                .setHead(Highflip.DataPushRequest.Head
                        .newBuilder()
                        .setMode(Highflip.DataMode.DENSE)
                        .build())
                .build();
        requests.onNext(head);

        Highflip.DataPushRequest body = Highflip.DataPushRequest
                .newBuilder()
                .setDense(Highflip.DenseData
                        .newBuilder()
                        .addRows(Highflip.DenseData.Row
                                .newBuilder()
                                .addAllValue(List.of("1", "2", "3"))
                                .build())
                        .build())
                .build();
        requests.onNext(body);

        requests.onCompleted();
    }

    @Test
    public void testListData() {

        Highflip.DataListRequest request = Highflip.DataListRequest
                .newBuilder()
                .build();

        List<String> result = Streams
                .stream(getStub().listData(request))
                .map(Highflip.DataListResponse::getDataId)
                .collect(Collectors.toList());

        for (String dataId : result) {
            log.info("data id: {}", dataId);
        }
    }

    @Test
    public void testListPartner() {

        Highflip.PartnerListRequest request = Highflip.PartnerListRequest
                .newBuilder()
                .build();

        List<String> result = Streams
                .stream(getStub().listPartner(request))
                .map(Highflip.PartnerListResponse::getPartnerId)
                .collect(Collectors.toList());

        for (String partnerId : result) {
            log.info("partner id: {}", partnerId);
        }
    }

}
