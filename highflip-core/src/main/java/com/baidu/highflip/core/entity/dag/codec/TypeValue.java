package com.baidu.highflip.core.entity.dag.codec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ByteString;

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
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.BYTES.getNumber()) {
            return value.getValue().getBytes();
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.LIST.getNumber()) {
            return listProtoConvert2List(value.getValue().getList());
        } else if (typeValue == HighflipMeta.TypedValueProto.TypeProto.MAP.getNumber()) {
            return mapProtoConvert2Map(value.getValue().getMap());
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static List<Object> listProtoConvert2List(
            HighflipMeta.ListProto listProto) {
        if (listProto == null) {
            return new ArrayList<>();
        }
        List<Object> result = new ArrayList<>();
        List<HighflipMeta.ValueProto> listProtoList = listProto.getListList();
        for (HighflipMeta.ValueProto valueProto : listProtoList) {
            result.add(convertValueProto(valueProto));
        }
        return result;
    }

    private static Map<String, Object> mapProtoConvert2Map(HighflipMeta.MapProto mapProto) {
        if (mapProto == null) {
            return new HashMap<>();
        }
        Map<String, HighflipMeta.ValueProto> map = mapProto.getMapMap();
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, HighflipMeta.ValueProto> entry :
                map.entrySet()) {
            String key = entry.getKey();
            HighflipMeta.ValueProto value = entry.getValue();
            result.put(key, convertValueProto(value));
        }
        return result;
    }

    private static Object convertValueProto(HighflipMeta.ValueProto value) {
        switch (value.getValueCase()) {
            case BOOL:
                return value.getBool();
            case INT:
                return value.getInt();
            case LONG:
                return value.getLong();
            case FLOAT:
                return value.getFloat();
            case DOUBLE:
                return value.getDouble();
            case STRING:
                return value.getString();
            case BYTES:
                return value.getBytes();
            case LIST:
                List<HighflipMeta.ValueProto> list =
                        value.getList().getListList();
                List<Object> resultList = new ArrayList<>();
                for(HighflipMeta.ValueProto listValue: list) {
                    resultList.add(convertValueProto(listValue));
                }
               return resultList;
            case MAP:
                return mapProtoConvert2Map(value.getMap());
            default:
                throw new RuntimeException("NOT_SUPPORTED_TYPE");
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
        } else if (object instanceof String) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.STRING);
            builder.setValue(HighflipMeta.ValueProto
                    .newBuilder()
                    .setString(object.toString())
                    .build());
        } else if (object instanceof ByteString) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.BYTES);
            builder.setValue(HighflipMeta.ValueProto
                                     .newBuilder()
                                     .setBytes((ByteString) object)
                                     .build());
        } else if (object instanceof HighflipMeta.ListProto) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.LIST);
            builder.setValue(HighflipMeta.ValueProto
                                     .newBuilder()
                                     .setList((HighflipMeta.ListProto) object)
                                     .build());
        } else if (object instanceof HighflipMeta.MapProto) {
            builder.setType(HighflipMeta.TypedValueProto.TypeProto.MAP);
            builder.setValue(HighflipMeta.ValueProto
                                     .newBuilder()
                                     .setMap((HighflipMeta.MapProto) object)
                                     .build());
        }
        return builder.build();
    }
}
