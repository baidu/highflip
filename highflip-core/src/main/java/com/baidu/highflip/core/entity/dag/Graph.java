package com.baidu.highflip.core.entity.dag;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Graph {

    private String name;

    private String description;

    private Map<String, Object> attributes;

    private List<Node> nodes;

    private List<Party> parties;
}
