package com.baidu.highflip.server.utils;

import highflip.v1.Highflip;

public class HighFlipUtils {

    public static Highflip.JobId toJobId(String jobId){
        return Highflip.JobId.newBuilder()
                .setJobId(jobId)
                .build();
    }
}
