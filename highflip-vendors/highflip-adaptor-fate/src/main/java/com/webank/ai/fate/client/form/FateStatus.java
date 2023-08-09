package com.webank.ai.fate.client.form;

import com.baidu.highflip.core.entity.runtime.basic.Status;

public enum FateStatus {

    WAITING("waiting"),
    RUNNING("running"),
    SUCCESS("success"),
    FAILED("failed"),
    ;
    FateStatus(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    public static FateStatus fromValue(String value) {
        for (FateStatus fateStatus : FateStatus.values()) {
            if (fateStatus.getValue().equalsIgnoreCase(value)) {
                return fateStatus;
            }
        }
        throw new IllegalArgumentException();
    }

    public static Status convertToEntity(FateStatus fateStatus) {
        switch (fateStatus) {
            case WAITING:
                return Status.APPENDING;
            case RUNNING:
                return Status.RUNNING;
            case FAILED:
                return Status.FAILED;
            case SUCCESS:
                return Status.SUCCEEDED;
        }
        throw new IllegalArgumentException();
    }

}
