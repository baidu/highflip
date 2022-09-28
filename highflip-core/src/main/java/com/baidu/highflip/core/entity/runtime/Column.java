package com.baidu.highflip.core.entity.runtime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Column{
    enum ColumnType{

    }

    int index;

    String name;

    String type;

    String description;
}