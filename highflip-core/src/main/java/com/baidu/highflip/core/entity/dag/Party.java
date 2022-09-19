package com.baidu.highflip.core.entity.dag;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Data
public class Party implements Serializable {

    private String name;

    private String role;

    private String description;

    private List<PartyNode> nodes;
}
