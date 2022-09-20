package com.baidu.highflip.core.entity.dag;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Party implements Serializable {

    private static final long serialVersionUID = 0x85710003L;

    private String name;

    private String role;

    private String description;

    private List<PartyNode> nodes;
}
