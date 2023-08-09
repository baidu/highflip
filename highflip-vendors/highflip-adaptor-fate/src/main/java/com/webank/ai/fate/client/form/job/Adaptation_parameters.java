/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

import lombok.Data;

@Data
public class Adaptation_parameters {

    private boolean if_initiator_baseline;

    private int request_task_cores;

    private int task_cores_per_node;

    private int task_memory_per_node;

    private int task_nodes;

}