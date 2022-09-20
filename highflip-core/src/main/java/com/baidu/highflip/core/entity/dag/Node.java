package com.baidu.highflip.core.entity.dag;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Node implements Serializable {

    private static final long serialVersionUID = 0x85710002L;

    String name;

    String type;

    String description;

    Map<String, Object> attributes;

    Map<String, String> inputs;

    Map<String, String> outputs;
}
