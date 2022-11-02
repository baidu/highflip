package com.webank.ai.fate.client.form;

import lombok.Data;

@Data
public class ResultForm<T> {

    Integer retcode;

    String retmsg;

    T data;
}
