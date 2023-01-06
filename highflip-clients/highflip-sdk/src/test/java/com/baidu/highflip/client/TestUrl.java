package com.baidu.highflip.client;

import com.baidu.highflip.client.common.GrpcURL;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestUrl {

    @Test
    public void testUrl() {
        String url = "grpc://user:pass@127.0.0.1:8751";

        GrpcURL grpc = GrpcURL.from(url);
        log.info("url = {}", grpc);

        Assertions.assertEquals("user", grpc.getUser());
        Assertions.assertEquals("pass", grpc.getPass());
    }

    @Test
    public void testSimpleUrl() {
        String url = "grpc://127.0.0.1:8751";

        GrpcURL grpc = GrpcURL.from(url);
        log.info("url = {}", grpc);

        Assertions.assertEquals("127.0.0.1", grpc.getHost());
    }
}
