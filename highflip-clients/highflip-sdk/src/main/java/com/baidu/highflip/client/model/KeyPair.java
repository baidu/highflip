package com.baidu.highflip.client.model;

import highflip.v1.Highflip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyPair {

    String key;

    String value;

    public static KeyPair of(String key, String value){
        return new KeyPair(key, value);
    }

    @Override
    public String toString(){
        return String.format("%s:%s", key, value);
    }

    public static Highflip.SparseData.Pair toProto(KeyPair pair){
        return Highflip.SparseData.Pair
                .newBuilder()
                .setKey(pair.getKey())
                .setValue(pair.getValue())
                .build();
    }

    public static KeyPair fromProto(Highflip.SparseData.Pair proto){
        return KeyPair.of(proto.getKey(), proto.getValue());
    }
}
