/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

import lombok.Data;

import java.util.List;

@Data
public class Roles {

    private List<Integer> guest;

    private List<Integer> host;

}