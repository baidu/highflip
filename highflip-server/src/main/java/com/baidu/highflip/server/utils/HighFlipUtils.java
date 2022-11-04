package com.baidu.highflip.server.utils;

import highflip.v1.Highflip;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HighFlipUtils {

    public static Highflip.JobId toJobId(String jobId) {
        return Highflip.JobId.newBuilder()
                .setJobId(jobId)
                .build();
    }

    public static Properties getProperty(String filename) {
        try (InputStream stream = HighFlipUtils.class.getResourceAsStream(filename)) {
            Properties props = new Properties();
            props.load(stream);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
