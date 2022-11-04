package com.webank.ai.fate.client.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class JobQueryResponseForm {

    /* TODO: not follow baidu code format
    @
    String f_job_id;

    String f_name;
    */

    @JsonProperty("f_status")
    String status;
}
