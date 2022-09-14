package com.baidu.highflip.core.entity.dag;

import lombok.Getter;

import java.util.Map;

@Getter
public class Node {

    String name;

    String type;

    String description;

    Map<String, Object> attributes;

    Map<String, String> inputs;

    Map<String, String> outputs;
}
