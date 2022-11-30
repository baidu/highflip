package com.baidu.highflip.client.model;

import highflip.HighflipMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class Schema {

    String name;

    String description;

    List<Column> columns;

    public static HighflipMeta.DataProto toProto(Schema schema){
        return HighflipMeta.DataProto
                .newBuilder()
                .setName(schema.name)
                .setDescription(schema.description)
                .addAllColumns(schema.columns
                        .stream()
                        .map(Column::toProto)
                        .collect(Collectors.toList()))
                .build();
    }
}
