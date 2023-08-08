
package com.webank.ai.fate.client.form.task;

import lombok.Data;


@Data
public class Provider_info {

    private Class_path class_path;
    private Env env;
    private String name;
    private String path;
    private String version;
}