package com.webank.ai.fate.client;


import com.webank.ai.fate.client.form.JsonResultForm;
import com.webank.ai.fate.client.form.ResultForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

@Slf4j
@Data
@Ignore
public class FateClientTest {

    public static final String TEST_FLOW_URL = "http://127.0.0.1:9380";

    FateClient client = FateClient.connect(TEST_FLOW_URL);

    @Test
    public void testVersionGet() {
        JsonResultForm result = getClient().version();
        log.info("result = {}", result.getData().toPrettyString());
    }

    @Test
    public void testJobList() {
        JsonResultForm result = getClient().jobList(10);
        log.info("result = {}", result.getData().toPrettyString());
    }

    @Test
    public void testJobQuery() {
        ResultForm<?> result = getClient().jobQuery("202210260752357956950");
        log.info("result = {}", result.getData().toString());
    }
}
