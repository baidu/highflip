package com.baidu.highflip.console.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtoUtils {

    public static String toJsonLine(Message msg){
        try {
            return JsonFormat.printer()
                    .omittingInsignificantWhitespace()
                    .print(msg);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Message msg){
        try {
            return JsonFormat.printer()
                    .print(msg);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Message> T fromJson(String json, Message.Builder builder){
        try {
            JsonFormat.parser().merge(json, builder);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
