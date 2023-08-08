package com.webank.ai.fate.client.form.dsl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonMapMapObjectDeserializer;
import lombok.Data;

import java.util.Map;

@Data
public class RoleConf {

    @JsonDeserialize(using = JsonMapMapObjectDeserializer.class)
    private Map<String, Map<String, Object>> host;

    @JsonDeserialize(using = JsonMapMapObjectDeserializer.class)
    private Map<String, Map<String, Object>> guest;

    @JsonDeserialize(using = JsonMapMapObjectDeserializer.class)
    private Map<String, Map<String, Object>> arbiter;

}
