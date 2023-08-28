
package com.webank.ai.fate.client.form.task;


import lombok.Data;

@Data
public class Common {

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
    private Pulsar_run pulsar_run;
    private Rabbitmq_run rabbitmq_run;
    private Spark_run spark_run;
    private String storage_engine;
    private int task_parallelism;
}