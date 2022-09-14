package com.baidu.highflip.core.entity.dag;

import lombok.Getter;

import java.util.List;

@Getter
public class Party{

    private String name;

    private String role;

    private String description;

    private List<PartyNode> nodes;
}
