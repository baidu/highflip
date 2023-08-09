
package com.webank.ai.fate.client.form.task;


import lombok.Data;

@Data
public class Component_parameters {

    private String CodePath;
    private ComponentParam ComponentParam;
    private String conf_path;
    private String dsl_path;
    private int dsl_version;
    private Initiator initiator;
    private Job_parameters job_parameters;
    private Local local;
    private String module;
    private Role role;

}