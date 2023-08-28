package com.webank.ai.fate.client.form.dsl;

import lombok.Data;

@Data
public class Component {

    private String module;

    private Input input;

    private Output output;

}
