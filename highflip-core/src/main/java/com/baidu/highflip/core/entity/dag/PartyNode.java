package com.baidu.highflip.core.entity.dag;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class PartyNode implements Serializable {

    private String name;

    private Map<String, Object> attributes;
}
