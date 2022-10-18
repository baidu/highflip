package com.webank.ai.fate.client;

import feign.Feign;
import feign.Param;
import feign.Request;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface FateClient {

    static FateClient connect(String url) {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .options(new Request.Options(
                        10, TimeUnit.SECONDS,
                        60, TimeUnit.SECONDS,
                        true))
                .target(FateClient.class, url);
    }

    @RequestLine("POST /job/submit")
    void jobSumbit(@Param("job_dsl") String dsl,
                   @Param("job_runtime_conf") String conf);

    @RequestLine("POST /job/list/job")
    List<String> jobList(@Param("limit") Integer limit);

    @RequestLine("POST /job/stop")
    void jobStop(@Param("job_id") Integer jobId);
}
