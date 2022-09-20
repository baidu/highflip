package com.baidu.highflip.core.entity.dag;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class Graph implements Serializable {

    private static final long serialVersionUID = 0x85710001L;

    String name;

    String description;

    Map<String, Object> attributes;

    List<Node> nodes;

    List<Party> parties;
}
