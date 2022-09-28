package com.baidu.highflip.core.utils;

import com.baidu.highflip.core.entity.runtime.Action;
import highflip.v1.Highflip;

public class ActionUtils {

    public static Action fromProto(Highflip.JobControlRequest.Action proto){
        return Action.valueOf(proto.name());
    }

    public static Highflip.JobControlRequest.Action toProto(Action object){
        return Highflip.JobControlRequest.Action.valueOf(object.name());
    }
}
