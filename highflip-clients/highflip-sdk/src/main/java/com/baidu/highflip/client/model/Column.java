package com.baidu.highflip.client.model;

import highflip.HighflipMeta;
import lombok.Data;

@Data
public class Column {

    String name;

    String type;

    String description = "";

    public static Column valueOf(String column, String defaultType){
        String[] item = column.split(":");
        Column ret = new Column();

        if(item.length == 1){
            ret.setName(item[0]);
            ret.setType(defaultType.toUpperCase());
        } else {
            ret.setName(item[0]);
            ret.setType(item[1]);
        }
        return ret;
    }

    public static Column valueOf(String column){
        return valueOf(column, "STRING");
    }

    public static HighflipMeta.DataProto.Column toProto(Column column){
        return HighflipMeta.DataProto.Column
                .newBuilder()
                .setName(column.name)
                .setType(column.type)
                .setDescription(column.description)
                .build();
    }


}
