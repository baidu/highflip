
package com.webank.ai.fate.client.form.task;
import lombok.Data;

import java.util.Date;


@Data
public class Transform_param {

    private Date transform_cols;
    private String transform_names;
    private String transform_type;
}