package com.baidu.highflip.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class IdGenerator {

    public static String fromStrings(String... args) {
        return toUUID(args);
    }

    protected static String toUUID(String[] args) {
        byte[] bytes = encodeMD5(args);
        return UUID.nameUUIDFromBytes(bytes).toString();
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
