package com.baidu.highflip.client.exception;

public class HighFlipException extends RuntimeException {

    public HighFlipException(String message) {
        super(message);
    }

    public HighFlipException(String message, Throwable cause) {
        super(message, cause);
    }
}
