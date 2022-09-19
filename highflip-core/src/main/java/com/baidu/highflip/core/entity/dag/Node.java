package com.baidu.highflip.core.entity.dag;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Node implements Serializable {

    String name;

    String type;

    String description;

    Map<String, Object> attributes;

    Map<String, String> inputs;

    Map<String, String> outputs;
}
