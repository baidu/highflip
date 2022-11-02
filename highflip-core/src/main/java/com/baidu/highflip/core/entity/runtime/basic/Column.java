package com.baidu.highflip.core.entity.runtime.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column {

    int index;

    String name;

    Type type;

    String description;
}