
package com.webank.ai.fate.client.form.task;

import com.baidu.highflip.core.entity.runtime.Task;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.common.deserializer.JsonListDeserializer;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;


@Data
public class FateTask {

    private int auto_retries;
    private int auto_retry_delay;
    @JsonDeserialize(using = JsonListDeserializer.class)
    private List<String> cmd;
    private String component_module;
    private String component_name;
    private Component_parameters component_parameters;
    private Date create_time;
    private int elapsed;
    private Date end_time;
    private Engine_conf engine_conf;
    private String federated_mode;
    private String federated_status_collect_type;
    private String initiator_party_id;
    private String initiator_role;
    // job_id is the job id in FATE
    private String job_id;
    private String party_id;
    private String party_status;
    private Provider_info provider_info;
    private String role;
    private String run_ip;
    private boolean run_on_this_party;
    private int run_pid;
    private Date start_time;
    private String status;
    private String status_code;
    private String task_id;
    private int task_version;
    private Date update_time;
    private String worker_id;

    public static Task convertToEntity(FateTask data) {
        Task task = new Task();
//        task.setTaskid();
        task.setJobid(data.getJob_id());
        task.setName(data.getComponent_name());
//        task.setDescription();
        task.setCreateTime(new DateTime(data.getCreate_time()));
        task.setUpdateTime(new DateTime(data.getUpdate_time()));
        task.setFinishTime(new DateTime(data.getEnd_time()));
        task.setNodeName(data.getComponent_name());
        task.setStatus(data.getStatus());
//        task.setMessage();
//        task.setIsDeleted();
//        task.setPrevious();
//        task.setNext();
        task.setBingingId(data.getTask_id());
//        task.setBinding();
        return task;
    }

}