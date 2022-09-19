package com.baidu.highflip.core.entity.dag;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class Graph implements Serializable {

    String name;

    String description;

    Map<String, Object> attributes;

    List<Node> nodes;

    List<Party> parties;
}
