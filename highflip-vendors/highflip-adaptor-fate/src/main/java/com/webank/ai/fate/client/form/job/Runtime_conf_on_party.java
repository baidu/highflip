/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.client.form.dsl.ComponentParameters;
import com.webank.ai.fate.client.form.dsl.Site;
import com.webank.ai.fate.common.deserializer.JsonMapStringDeserializer;
import lombok.Data;

import java.util.Map;

@Data
public class Runtime_conf_on_party {

    private ComponentParameters component_parameters;

    private String conf_path;

    private String dsl_path;

    private int dsl_version;

    private Site initiator;

    private Job_parameters job_parameters;

    @JsonDeserialize(using = JsonMapStringDeserializer.class)
    private Map<String, String> role;

}