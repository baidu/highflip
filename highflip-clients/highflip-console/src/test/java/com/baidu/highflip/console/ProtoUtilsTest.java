package com.baidu.highflip.console;

import com.baidu.highflip.console.utils.ProtoUtils;
import highflip.v1.Highflip;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class ProtoUtilsTest {

    @Test
    public void testToJson() {
        Highflip.PlatformVersion version = Highflip.PlatformVersion
                .newBuilder()
                .setCompany("baidu")
                .setProduct("test")
                .setVersion("1.0.0")
                .build();

        String json = ProtoUtils.toJson(version);
        log.info("tojson: {}", json);
    }
}
