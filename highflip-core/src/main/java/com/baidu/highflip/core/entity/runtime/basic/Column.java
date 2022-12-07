package com.baidu.highflip.core.entity.runtime.basic;

import highflip.HighflipMeta;
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

    public static Column fromProto(HighflipMeta.DataProto.Column proto) {
        return new Column(
                proto.getIndex(),
                proto.getName(),
                Type.valueOf(proto.getType().toUpperCase()),
                proto.getDescription());
    }

    public static HighflipMeta.DataProto.Column toProto(Column data) {
        return HighflipMeta.DataProto.Column
                .newBuilder()
                .setIndex(data.index)
                .setName(data.name)
                .setType(data.type.toString())
                .setDescription(data.description)
                .build();
    }
}