package com.baidu.highflip.core.utils;

import com.baidu.highflip.core.entity.runtime.basic.Status;
import highflip.v1.Highflip;

public class JobStatusUtils {

    public Status fromProto(Highflip.JobCheckResponse.JobStatus proto) {
        return Status.valueOf(proto.name());
    }

    Highflip.JobCheckResponse.JobStatus toProto(Status status) {
        return Highflip.JobCheckResponse.JobStatus.valueOf(status.name());
    }
}
