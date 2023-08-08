package com.webank.ai.fate.adaptor;

import static com.webank.ai.fate.common.FateConstants.DATA_ID_SEPARATOR;

import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.basic.DataCategory;
import com.baidu.highflip.core.entity.runtime.basic.KeyPair;
import com.baidu.highflip.core.utils.Foreach;
import com.webank.ai.fate.client.form.ResultForm;
import com.webank.ai.fate.common.DataMultipartFile;
import com.webank.ai.fate.common.DecompressUtils;
import com.webank.ai.fate.context.FateContext;
import feign.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class DataAdaptor implements com.baidu.highflip.core.adaptor.DataAdaptor {

    private final FateContext context;

    private final String DEFAULT_NAMESPACES = "HIGH-FLIP";

    private final String DEFAULT_DELIMITER = ",";

    public DataAdaptor(FateContext context) {
        this.context = context;
    }

    @Override
    public Data updateData(Data data) {
        return null;
    }

    @Override
    public int getDataCount() {
        return 0;
    }

    @Override
    public Data getDataByIndex(int index, Data data) {
        return null;
    }

    @Override
    public void deleteData(Data data) {
        getContext().getClient().deleteData(data.getBingingId(), DEFAULT_NAMESPACES);
    }

    @Override
    public InputStream readDataRaw(Data data) {
        return null;
    }

    @Override
    public Iterator<List<Object>> readDataDense(Data data) {
        if(data == null) {
            throw new RuntimeException("data is null.");
        }
        log.info("data:{}", data);

        if (DataCategory.RESULT_DATA.equals(data.getCategory())) {
            String jobId = (String) data.getBinding().get("jobId");
            String componentName = (String) data.getBinding().get("componentName");
            String role = (String) data.getBinding().get("role");
            String partyId = (String) data.getBinding().get("partyId");
            log.info("jobId: {}, componentName: {}, role: {}, partyId: {}",
                     jobId, componentName, role, partyId);

            try (Response response = getContext().getClient()
                                                 .downloadComponentResultData(jobId, componentName,
                                                                              role, partyId)) {
                String content = DecompressUtils.decompressTarGzToStringMap(
                        response.body().asInputStream(),
                        s -> s.contains("csv")).get("data.csv");
                return Arrays.stream(content.split("\n"))
                             .map(s -> Arrays.stream(s.split(DEFAULT_DELIMITER))
                                             .map(d -> (Object) d)
                                             .collect(Collectors.toList()))
                             .collect(Collectors.toList()).iterator();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try (Response response = getContext().getClient()
                                                 .downloadData(data.getName(),
                                                               DEFAULT_NAMESPACES)) {
                String content = DecompressUtils.decompressTarGzToStringMap(
                        response.body().asInputStream(),
                        s -> s.contains("csv")).get("table.csv");
                return Arrays.stream(content.split("\n"))
                             .map(s -> Arrays.stream(s.split(DEFAULT_DELIMITER))
                                             .map(d -> (Object) d)
                                             .collect(Collectors.toList()))
                             .collect(Collectors.toList()).iterator();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Iterator<List<KeyPair>> readDataSparse(Data data) {
        return null;
    }

    @Override
    public Data createData(Data data) {
        return null;
    }

    @Override
    public void writeDataRaw(Data data, InputStream body) {

    }

    @Override
    public void writeDataDense(Data data, Iterator<List<Object>> body) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String headers =
                    data.getColumns().stream().map(c -> c.getName())
                        .collect(Collectors.joining(DEFAULT_DELIMITER));
            stringBuilder.append(headers).append("\n");
            final Foreach<List<Object>> foreach = Foreach.from(body);
            for (List<Object> dataList : foreach) {
                String column = dataList.stream().map(Object::toString).collect(
                        Collectors.joining(DEFAULT_DELIMITER));
                stringBuilder.append(column).append("\n");
            }
        } catch (NoSuchElementException e) {
           log.warn("body has no next element");
        } catch (Exception e) {
            throw e;
        }
        String tableName = data.getName();
        log.info("push table:{} data:{}", tableName, data);

        MultipartFile multipartFile = new DataMultipartFile(tableName,
                stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        // drop default 1, which means overwrite the dataset
        ResultForm<com.webank.ai.fate.client.form.data.Data> dataResultForm =
                getContext().getClient()
                            .pushData(multipartFile, DEFAULT_DELIMITER, "1",
                                      "4", tableName, DEFAULT_NAMESPACES,
                                      null, null,
                                      null, "1");
        data.setBingingId(dataResultForm.getData().getJob_id());
    }

    @Override
    public void writeDataSparse(Data data, Iterator<List<KeyPair>> body) {

    }
}
