package com.baidu.highflip.server.adaptor;

import com.baidu.highflip.server.adapter.impl.ConfigurablePartnerAdaptor;
import com.baidu.highflip.server.utils.HighFlipUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@Slf4j
@SpringBootTest
public class TestAdaptor {

    @Test
    public void testPartnerAdaptor() {
        Properties props = HighFlipUtils.getProperty("/adaptor/highflip.partner.properties");
        log.info("properties = {}", props);

        ConfigurablePartnerAdaptor adaptor = new ConfigurablePartnerAdaptor(props);
        log.info("adaptor = {}", adaptor.getPartners());
    }
}
