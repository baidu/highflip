package com.baidu.highflip.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Slf4j
@Disabled
public class TestClient {

    @Test
    public void testAuth(){
        HighFlipClient client = new HighFlipClient();
        client.connect("grpc://user:pass@127.0.0.1:8751");

        var response = client.getPlatform();
        log.info("platform get = {}", response);
    }

    @Test
    public void testGuestAuth(){
        HighFlipClient client = new HighFlipClient();
        client.connect("grpc://127.0.0.1:8751");

        var response = client.getPlatform();
        log.info("platform get = {}", response);
    }
}
