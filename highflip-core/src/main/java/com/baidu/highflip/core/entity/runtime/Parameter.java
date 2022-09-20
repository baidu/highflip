package com.baidu.highflip.core.entity.runtime;

import lombok.Data;

import java.io.Serializable;

@Data
public class Parameter implements Serializable {

    String name;

    String type;

    String description;

    Boolean required;
}
