package com.baidu.highflip.client.model;

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
}
