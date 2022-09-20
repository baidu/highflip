package com.baidu.highflip.core.entity.dag;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class PartyNode implements Serializable {

    private static final long serialVersionUID = 0x85710004L;

    private String name;

    private Map<String, Object> attributes;
}
