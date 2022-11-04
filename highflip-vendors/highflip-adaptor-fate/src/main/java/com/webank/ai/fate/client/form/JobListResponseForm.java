package com.webank.ai.fate.client.form;

import lombok.Data;

import java.util.List;

@Data
public class JobListResponseForm {

    Integer count;

    List<String> jobs;
}
