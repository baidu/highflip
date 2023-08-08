/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

import lombok.Data;

import java.util.List;
@Data
public class Input {

    private InputData data;

    @Data
    public static class InputData {

        private List<String> data;
    }

}