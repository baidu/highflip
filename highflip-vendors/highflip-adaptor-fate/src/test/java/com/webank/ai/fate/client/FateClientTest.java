package com.webank.ai.fate.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.webank.ai.fate.client.form.ResultForm;
import com.webank.ai.fate.client.form.dsl.Dsl;
import com.webank.ai.fate.client.form.dsl.DslConf;
import com.webank.ai.fate.client.form.job.FateJob;
import com.webank.ai.fate.client.form.job.JobData;
import com.webank.ai.fate.client.form.task.TaskData;
import com.webank.ai.fate.common.DataMultipartFile;
import com.webank.ai.fate.common.DecompressUtils;
import com.webank.ai.fate.common.deserializer.SerializerUtils;
import feign.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Data
@Disabled
public class FateClientTest {

    public static final String TEST_FLOW_URL = "http://10.27.130.41:8380";

    FateClient client = FateClient.connect(TEST_FLOW_URL);

    //    @Test
    //    public void testVersionGet() {
    //        JsonResultForm result = getClient().version();
    //        log.info("result = {}", result.getData().toPrettyString());
    //    }

    @Test
    public void testJobList() {
        ResultForm<JobData> result = getClient().listJob(1, 10);
        log.info("result = {}", result);
    }

    @Test
    public void testTaskList() {
        ResultForm<TaskData> result = getClient().listTask(null, 18, 1);
        log.info("result = {}", result);
    }

    @Test
    public void testJobQuery() {
        ResultForm<?> result = getClient().jobQuery("202212011128410103660");
        log.info("result = {}", result.getData().toString());
    }

    @Test
    public void testPushData() {
        String data = "id,y,x0,x1,x2,x3,x4,x5\n" + "274,0,0.963102,1.467675,0.829202,0.772457,-0.038076,-0.468613\n"
                + "199,0,0.426758,0.723479,0.316885,0.287273,1.000835,0.962702\n"
                + "133,1,0.254879,-1.046633,0.209656,0.074214,-0.441366,-0.377645\n"
                + "273,1,-1.142928,-0.781198,-1.166747,-0.923578,0.62823,-1.021418\n"
                + "551,1,-0.879933,0.420589,-0.877527,-0.780484,-1.037534,-0.48388\n"
                + "175,1,-1.451067,-1.406518,-1.456564,-1.092337,-0.708765,-1.168557\n";
        MultipartFile multipartFile = new DataMultipartFile("unittest_data3.csv", data.getBytes(StandardCharsets.UTF_8));
        ResultForm<?> resultForm = getClient().pushData(multipartFile, ",", "1", "4", "highflip_test1", "experiment", null, null, null,"1");
        log.info("result = {}", resultForm);
    }

    @Test
    public void testJobSubmit() throws JsonProcessingException {
        Dsl dsl = SerializerUtils.deserialize("{\n" + "    \"components\": {\n" + "        \"reader_0\": {\n"
                + "            \"module\": \"Reader\",\n" + "            \"output\": {\n"
                + "                \"data\": [\n" + "                    \"data\"\n" + "                ]\n"
                + "            }\n" + "        },\n" + "        \"data_transform_0\": {\n"
                + "            \"module\": \"DataTransform\",\n" + "            \"input\": {\n"
                + "                \"data\": {\n" + "                    \"data\": [\n"
                + "                        \"reader_0.data\"\n" + "                    ]\n" + "                }\n"
                + "            },\n" + "            \"output\": {\n" + "                \"data\": [\n"
                + "                    \"data\"\n" + "                ],\n" + "                \"model\": [\n"
                + "                    \"model\"\n" + "                ]\n" + "            }\n" + "        },\n"
                + "        \"homo_nn_0\": {\n" + "            \"module\": \"HomoNN\",\n" + "            \"input\": {\n"
                + "                \"data\": {\n" + "                    \"train_data\": [\n"
                + "                        \"data_transform_0.data\"\n" + "                    ]\n"
                + "                }\n" + "            },\n" + "            \"output\": {\n"
                + "                \"data\": [\n" + "                    \"data\"\n" + "                ],\n"
                + "                \"model\": [\n" + "                    \"model\"\n" + "                ]\n"
                + "            }\n" + "        }\n" + "    }\n" + "}", Dsl.class);
        DslConf dslConf = SerializerUtils.deserialize("{\n" + "    \"dsl_version\": 2,\n" + "    \"initiator\": {\n"
                + "        \"role\": \"guest\",\n" + "        \"party_id\": 10000\n" + "    },\n" + "    \"role\": {\n"
                + "        \"arbiter\": [\n" + "            10000\n" + "        ],\n" + "        \"host\": [\n"
                + "            10000\n" + "        ],\n" + "        \"guest\": [\n" + "            10000\n"
                + "        ]\n" + "    },\n" + "    \"component_parameters\": {\n" + "        \"common\": {\n"
                + "            \"data_transform_0\": {\n" + "                \"with_label\": true,\n"
                + "                \"label_name\": \"y\",\n" + "                \"label_type\": \"int\",\n"
                + "                \"output_format\": \"dense\"\n" + "            },\n"
                + "            \"homo_nn_0\": {\n" + "                \"config_type\": \"pytorch\",\n"
                + "                \"nn_define\": [\n" + "                    {\n"
                + "                        \"layer\": \"Linear\",\n" + "                        \"name\": \"line\",\n"
                + "                        \"type\": \"normal\",\n" + "                        \"config\": [\n"
                + "                            30,\n" + "                            1\n"
                + "                        ]\n" + "                    },\n" + "                    {\n"
                + "                        \"layer\": \"Sigmoid\",\n"
                + "                        \"type\": \"activate\",\n"
                + "                        \"name\": \"sigmoid\"\n" + "                    }\n" + "                ],\n"
                + "                \"batch_size\": -1,\n" + "                \"optimizer\": {\n"
                + "                    \"optimizer\": \"Adam\",\n" + "                    \"lr\": 0.05\n"
                + "                },\n" + "                \"early_stop\": {\n"
                + "                    \"early_stop\": \"diff\",\n" + "                    \"eps\": 0.0001\n"
                + "                },\n" + "                \"loss\": \"BCELoss\",\n"
                + "                \"metrics\": [\n" + "                    \"accuracy\"\n" + "                ],\n"
                + "                \"max_iter\": 2\n" + "            }\n" + "        },\n" + "        \"role\": {\n"
                + "            \"host\": {\n" + "                \"0\": {\n" + "                    \"reader_0\": {\n"
                + "                        \"table\": {\n"
                + "                            \"name\": \"breast_homo_host\",\n"
                + "                            \"namespace\": \"HIGH-FLIP\"\n" + "                        }\n"
                + "                    }\n" + "                }\n" + "            },\n" + "            \"guest\": {\n"
                + "                \"0\": {\n" + "                    \"reader_0\": {\n"
                + "                        \"table\": {\n"
                + "                            \"name\": \"breast_homo_guest\",\n"
                + "                            \"namespace\": \"HIGH-FLIP\"\n" + "                        }\n"
                + "                    }\n" + "                }\n" + "            }\n" + "        }\n" + "    }\n"
                + "}", DslConf.class);
        log.info("dsl:{}", SerializerUtils.toJsonString(dsl));
        log.info("dsl conf:{}", SerializerUtils.toJsonString(dslConf));
        ResultForm<FateJob> job = getClient().jobSubmit(dsl, dslConf);
        log.info("result = {}", job);
    }

    @Test
    public void testDownloadData() {
        try(Response response = getClient().downloadData("unittest_data3", "experiment")) {
            InputStream inputStream = response.body().asInputStream();
            Map<String, String> result = DecompressUtils.decompressTarGzToStringMap(inputStream, s -> s.contains("csv"));
            log.info("result = {}, head = {}", result, response.headers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDownloadComponentResultData(){
        String jobId = "202305231618271012050";
        String componentName = "intersect_0";
        String role = "guest";
        String partyId = "9999";
        try (Response response = getClient().downloadComponentResultData(jobId, componentName, role, partyId)) {
            InputStream inputStream = response.body().asInputStream();

            Map<String, String> result = DecompressUtils.decompressTarGzToStringMap(inputStream, s -> s.contains("csv"));
            log.info("result = {}, head = {}", result, response.headers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
