package com.baidu.highflip.core.entity.runtime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Column{

    int index;

    String name;

    Type type;

    String description;
}