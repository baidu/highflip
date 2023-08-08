package com.webank.ai.fate.client.form.job;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webank.ai.fate.client.form.dsl.Dsl;
import com.webank.ai.fate.common.deserializer.JsonMapStringDeserializer;
import com.webank.ai.fate.common.deserializer.JsonStringDeserializer;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class QueryJob {

    private long f_apply_resource_time;

    private boolean f_cancel_signal;

    private String f_cancel_time;

    private int f_cores;

    private String f_create_date;

    private Date f_create_time;

    private String f_description;

    private Dsl f_dsl;

    private long f_elapsed;

    private String f_end_date;

    private int f_end_scheduling_updates;

    private Date f_end_time;

    private String f_engine_name;

    private String f_engine_type;

    private String f_initiator_party_id;

    private String f_initiator_role;

    private boolean f_is_initiator;

    private String f_job_id;

    private int f_memory;

    private String f_name;

    private List<Integer> f_partners;

    private int f_party_id;

    private int f_progress;

    private boolean f_ready_signal;

    private String f_ready_time;

    private int f_remaining_cores;

    private int f_remaining_memory;

    private boolean f_rerun_signal;

    private boolean f_resource_in_use;

    private long f_return_resource_time;

    @JsonDeserialize(using = JsonStringDeserializer.class)
    private String f_role;

    @JsonDeserialize(using = JsonMapStringDeserializer.class)
    private Map<String, String> f_roles;

    private Runtime_conf f_runtime_conf;

    private Runtime_conf_on_party f_runtime_conf_on_party;

    private String f_start_date;

    private Date f_start_time;

    private String f_status;

    private String f_status_code;

    private String f_tag;

    private Train_runtime_conf f_train_runtime_conf;

    private String f_update_date;

    private Date f_update_time;

    private User f_user;

    private String f_user_id;

}
