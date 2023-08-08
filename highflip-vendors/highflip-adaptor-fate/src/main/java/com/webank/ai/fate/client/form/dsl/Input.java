package com.webank.ai.fate.client.form.dsl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonListDeserializer;
import com.webank.ai.fate.common.deserializer.JsonMapListStringDeserializer;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Input {

    @JsonDeserialize(using = JsonMapListStringDeserializer.class)
    private Map<String, List<String>> data;

    @JsonDeserialize(using = JsonListDeserializer.class)
    private List<String> model;

    @JsonDeserialize(using = JsonListDeserializer.class)
    private List<String> cache;

}
