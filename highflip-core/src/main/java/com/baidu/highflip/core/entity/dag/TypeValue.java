package com.baidu.highflip.core.entity.dag;

import highflip.HighflipMeta;
import org.hibernate.cfg.NotYetImplementedException;

public class TypeValue{
    public static Object fromProto(HighflipMeta.TypedValueProto value){
        int typeValue = value.getTypeValue();
        if(typeValue == HighflipMeta.TypedValueProto.TypeProto.BOOLEAN.getNumber()){
            return value.getValue().getBool();
        } else if(typeValue == HighflipMeta.TypedValueProto.TypeProto.INT.getNumber()) {
            return value.getValue().getInt();
        } else if(typeValue == HighflipMeta.TypedValueProto.TypeProto.LONG.getNumber()) {
            return value.getValue().getLong();
        } else if(typeValue == HighflipMeta.TypedValueProto.TypeProto.DOUBLE.getNumber()) {
            return value.getValue().getDouble();
        } else if(typeValue == HighflipMeta.TypedValueProto.TypeProto.FLOAT.getNumber()) {
            return value.getValue().getFloat();
        } else if(typeValue == HighflipMeta.TypedValueProto.TypeProto.STRING.getNumber()) {
            return value.getValue().getString();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static HighflipMeta.TypedValueProto toProto(Object object) {
        throw new NotYetImplementedException();
    }
}
