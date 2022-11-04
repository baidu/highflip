package com.baidu.highflip.server;

import com.baidu.highflip.server.adapter.impl.ConfigurablePartnerAdaptor;
import com.baidu.highflip.server.utils.HighFlipUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdaptor {

    @Test
    public void testPartnerAdaptor(){
        Properties props = HighFlipUtils.getProperty("/adaptor/highflip.partner.properties");
        log.info("properties = {}", props);

        ConfigurablePartnerAdaptor adaptor = new ConfigurablePartnerAdaptor(props);
        log.info("adaptor = {}", adaptor.getPartners());
    }
}
