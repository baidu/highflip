package com.webank.ai.fate.client.form.job;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonMapStringDeserializer;
import lombok.Data;

import java.util.Map;

@Data
public class User {

    @JsonDeserialize(using = JsonMapStringDeserializer.class)
    private Map<String,String> guest;

    @JsonDeserialize(using = JsonMapStringDeserializer.class)
    private Map<String,String> host;

}