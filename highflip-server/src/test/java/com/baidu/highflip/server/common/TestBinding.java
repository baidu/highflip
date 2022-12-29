package com.baidu.highflip.server.common;

import com.baidu.highflip.core.entity.runtime.basic.Binding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestBinding {

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testBinding() throws Exception {
        Binding binding = new Binding();
        binding.setString("name", "hello");

        String json = mapper.writeValueAsString(binding);
        log.info("json={}", json);
    }
}
