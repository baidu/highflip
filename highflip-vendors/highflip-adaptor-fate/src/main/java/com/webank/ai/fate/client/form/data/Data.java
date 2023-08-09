package com.webank.ai.fate.client.form.data;

import java.util.Map;

@lombok.Data
public class Data {

    private String board_url;
    private int code;
    private String dsl_path;
    private String job_id;
    private String logs_directory;
    private String message;
    private Map<String, String> model_info;
    private String namespace;
    private String pipeline_dsl_path;
    private String runtime_conf_on_party_path;
    private String runtime_conf_path;
    private String table_name;
    private String train_runtime_conf_path;
}