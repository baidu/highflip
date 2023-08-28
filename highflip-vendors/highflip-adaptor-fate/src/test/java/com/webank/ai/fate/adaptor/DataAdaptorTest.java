package com.webank.ai.fate.adaptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.baidu.highflip.core.utils.Foreach;

public class DataAdaptorTest {
    private final String DEFAULT_DELIMITER = ",";

    @Test
    public  void writeDataDenseTest() {
        List<List<Object>> body = new ArrayList<>();
        List<Object> rowHeader = List.of("ID","name");
        List<Object> rowOne = List.of("1","bob");
        List<Object> rowTwo = List.of("2","joe");
        List<Object> rowThree = List.of("3","alice");
        body.add(rowHeader);
        body.add(rowOne);
        body.add(rowTwo);
        body.add(rowThree);
        Iterator<List<Object>> iterator = body.iterator();

        StringBuilder stringBuilder = new StringBuilder();
        for (List<Object> dataList : Foreach.from(iterator)) {
            String column = dataList.stream().map(Object::toString).collect(
                    Collectors.joining(DEFAULT_DELIMITER));
            stringBuilder.append(column).append("\n");
        }
    }

    @Test
    public void testSplit(){
        String dataId = "8a53e2c4-7e04-482b-9eef-4dd6c8376b1e+intersect_0" +
                        "+guest+9999";
        final String[] split = dataId.split("\\+");
        System.out.println(List.of(split));
    }

}
