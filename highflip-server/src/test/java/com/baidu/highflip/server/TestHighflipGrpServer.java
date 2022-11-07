package com.baidu.highflip.server;

import com.baidu.highflip.server.common.GrpcServerTestBase;
import com.google.common.collect.Streams;
import highflip.v1.HighFlipGrpc;
import highflip.v1.Highflip;
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
