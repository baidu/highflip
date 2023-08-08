package com.webank.ai.fate.client.form.task;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.baidu.highflip.core.entity.runtime.Task;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonListDeserializer;

import lombok.Data;

@Data
public class FFateTask {
    private int f_auto_retries;
    private int f_auto_retry_delay;
    @JsonDeserialize(using = JsonListDeserializer.class)
    private List<String> f_cmd;
    private String f_component_module;
    private String f_component_name;
    private Component_parameters f_component_parameters;
    private Date f_create_time;
    private int f_elapsed;
    private Date f_end_time;
    private Engine_conf f_engine_conf;
    private String f_federated_mode;
    private String f_federated_status_collect_type;
    private String f_initiator_party_id;
    private String f_initiator_role;
    // job_id is the job id in FATE
    private String f_job_id;
    private String f_party_id;
    private String f_party_status;
    private Provider_info f_provider_info;
    private String f_role;
    private String f_run_ip;
    private boolean f_run_on_this_party;
    private int f_run_pid;
    private Date f_start_time;
    /**
     * f_status is one of waiting,running,canceled,timeout,failed,pass,success
     */
    private String f_status;
    private String f_status_code;
    private String f_task_id;
    private int f_task_version;
    private Date f_update_time;
    private String f_worker_id;

    public static Task convertToEntity(FFateTask data) {
        Task task = new Task();
        task.setJobid(data.getF_job_id());
        task.setName(data.getF_component_name());
        task.setCreateTime(new DateTime(data.getF_create_time()));
        task.setUpdateTime(new DateTime(data.getF_update_time()));
        task.setFinishTime(new DateTime(data.getF_end_time()));
        task.setNodeName(data.getF_component_name());
        task.setStatus(data.getF_status());
        task.setBingingId(data.getF_task_id());
        return task;
    }

}
