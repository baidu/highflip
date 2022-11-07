package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.entity.runtime.Platform;
import com.baidu.highflip.server.respository.PlatformRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Slf4j
@SpringBootTest
public class TestPlatform {

    @Autowired
    PlatformRepository platforms;

    @Test
    @Transactional
    public void testDatabase() {
        log.info("test list");

        platforms.deleteLocal();

        for (Platform plat : platforms.findAll()) {
            log.info("platform: {}", plat);
        }
    }
}
