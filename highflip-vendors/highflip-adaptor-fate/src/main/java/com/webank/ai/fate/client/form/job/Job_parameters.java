/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

import lombok.Data;

import java.util.Map;

@Data
public class Job_parameters {

    private Adaptation_parameters adaptation_parameters;

    private int auto_retries;

    private int auto_retry_delay;

    private String computing_engine;

    private int computing_partitions;

    private Eggroll_run eggroll_run;

    private Engines_address engines_address;

    private String federated_mode;

    private String federated_status_collect_type;

    private String federation_engine;

    private String job_type;

    private String model_id;

    private String model_version;

    private Map<String,Object> pulsar_run;

    private Map<String,Object> rabbitmq_run;

    private Map<String,Object> spark_run;

    private String storage_engine;

    private int task_parallelism;

}