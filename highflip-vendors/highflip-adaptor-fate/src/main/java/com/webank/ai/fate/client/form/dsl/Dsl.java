package com.webank.ai.fate.client.form.dsl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonMapComponentsDeserializer;
import lombok.Data;

import java.util.Map;

@Data
public class Dsl {

    @JsonDeserialize(using = JsonMapComponentsDeserializer.class)
    Map<String, Component> components;

}
