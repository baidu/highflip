package com.webank.ai.fate.client.form.dsl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonMapListStringDeserializer;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DslConf {

    private String dsl_version;

    private Site initiator;

    @JsonDeserialize(using = JsonMapListStringDeserializer.class)
    private Map<String, List<String>> role;

    private ComponentParameters component_parameters;

}
