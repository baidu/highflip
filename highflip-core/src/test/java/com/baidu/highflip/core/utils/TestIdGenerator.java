package com.baidu.highflip.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class TestIdGenerator {

    @Test
    public void testUUID() {
        String result1 = IdGenerator.fromStrings("123", "456");
        log.info("uuid(123, 456) => {}", result1);

        String result2 = IdGenerator.fromStrings("123456");
        log.info("uuid(123456) => {}", result2);

        Assert.assertEquals(result1, result2);
    }

    @Test
    public void testMd5() {
        String md5 = IdGenerator.toHex(new String[]{"123456"});
        log.info("md5(123456) => {}", md5);

        String uuid = IdGenerator.fromStrings("123456");
        log.info("uuid(123456) => {}", uuid);

        Assert.assertEquals(md5.substring(0, 8), uuid.substring(0, 8));
    }
}
