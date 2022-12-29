package com.baidu.highflip.core.entity.dag.codec;

import highflip.HighflipMeta;

public class TypeValue {
    public static Object fromProto(HighflipMeta.TypedValueProto value) {
        int typeValue = value.getTypeValue();
        if (typeValue == HighflipMeta.TypedValueProto.TypeProto.BOOLEAN.getNumber()) {
            return value.getValue().getBool();
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.INT.getNumber()) {
            return value.getValue().getInt();
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.LONG.getNumber()) {
            return value.getValue().getLong();
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.DOUBLE.getNumber()) {
            return value.getValue().getDouble();
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.FLOAT.getNumber()) {
            return value.getValue().getFloat();
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.STRING.getNumber()) {
            return value.getValue().getString();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static HighflipMeta.TypedValueProto toProto(Object object) {
        HighflipMeta.TypedValueProto.Builder builder = HighflipMeta.TypedValueProto
                .newBuilder();

        if (object instanceof Boolean) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.BOOLEAN);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setBool((Boolean) object)
                    .build());
        } else if (object instanceof Integer) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.INT);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setInt((Integer) object)
                    .build());
        } else if (object instanceof Long) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.LONG);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setLong((Long) object)
                    .build());
        } else if (object instanceof Float) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.FLOAT);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setFloat((Float) object)
                    .build());
        } else if (object instanceof Double) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.DOUBLE);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setDouble((Double) object)
                    .build());
        } else {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.STRING);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setString(object.toString())
                    .build());
        }

        return builder.build();
    }
}
