package com.baidu.highflip.core.entity.runtime.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyPair {

    public String key;

    public Object value;

    public static KeyPair of(String key, Object value){
        return new KeyPair(key, value);
    }
}
