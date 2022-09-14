package com.baidu.highflip.core.entity.dag;

import lombok.Getter;

import java.util.Map;

@Getter
public class PartyNode {

    private String name;

    private Map<String, Object> attributes;
}
