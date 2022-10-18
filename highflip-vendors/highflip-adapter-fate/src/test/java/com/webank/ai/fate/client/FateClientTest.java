package com.webank.ai.fate.client;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FateClientTest {

    @Test
    public void testConnect(){
        FateClient  client = FateClient.connect("http://www.sina.com");
        log.info("fate_client = {}", client);
    }
}
