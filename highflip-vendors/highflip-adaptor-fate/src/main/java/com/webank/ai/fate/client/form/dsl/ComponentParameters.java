package com.webank.ai.fate.client.form.dsl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonMapMapObjectDeserializer;
import lombok.Data;

import java.util.Map;

@Data
public class ComponentParameters {

    @JsonDeserialize(using = JsonMapMapObjectDeserializer.class)
    private Map<String, Map<String, Object>> common;

    private RoleConf role;

}
