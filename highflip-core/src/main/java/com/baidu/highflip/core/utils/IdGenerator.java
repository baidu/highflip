package com.baidu.highflip.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringJoiner;
import java.util.UUID;

import static ch.qos.logback.core.encoder.ByteArrayUtil.toHexString;


public class IdGenerator {

    public static String fromStrings(String... args) {
        return toUUID(args);
    }

    protected static String toUUID(String[] args) {
        StringJoiner joiner = new StringJoiner("");
        for(String item: args){
            joiner.add(item);
        }
        byte[] bytes = joiner.toString().getBytes();
        return UUID.nameUUIDFromBytes(bytes).toString();
    }

    public static String toHex(String[] args){
        byte[] bytes = encodeMD5(args);
        return toHexString(bytes);
    }

    protected static byte[] encodeMD5(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (String item : args) {
                md.update(item.getBytes());
            }
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
