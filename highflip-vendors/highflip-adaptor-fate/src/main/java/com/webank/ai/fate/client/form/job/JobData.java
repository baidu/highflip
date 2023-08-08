/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

import lombok.Data;

import java.util.List;

@Data
public class JobData {

    private int count;

    private List<FateJob> jobs;

}