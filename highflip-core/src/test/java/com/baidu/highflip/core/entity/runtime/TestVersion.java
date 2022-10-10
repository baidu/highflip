package com.baidu.highflip.core.entity.runtime;

import com.baidu.highflip.core.entity.runtime.version.PlatformVersion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


@Slf4j
public class TestVersion {

    @Test
    public void testCompare(){
        PlatformVersion ver1 = new PlatformVersion("baidu", "highflip", "1.1.1");

        PlatformVersion ver2 = new PlatformVersion("baidu", "highflip", "1.2.0");

        int rest = ver1.compareTo(ver2);
        log.info("compare {} with {} => {}", ver1, ver2, rest);

        Assertions.assertEquals(rest, -1);
    }

    @Test
    public void testEqual(){
        PlatformVersion ver1 = new PlatformVersion("baidu", "highflip", "1.1");

        PlatformVersion ver2 = new PlatformVersion("baidu", "highflip", "1.1.0");

        int rest = ver1.compareTo(ver2);
        log.info("compare {} with {} => {}", ver1, ver2, rest);

        Assertions.assertEquals(rest, 0);
    }
}
