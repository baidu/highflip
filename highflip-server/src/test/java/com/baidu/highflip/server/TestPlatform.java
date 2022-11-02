package com.baidu.highflip.server;

import com.baidu.highflip.core.entity.runtime.Platform;
import com.baidu.highflip.server.respository.PlatformRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Slf4j
@RunWith(SpringRunner.class)
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
