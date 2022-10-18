package com.baidu.highflip.exception;

public class HighFlipException extends RuntimeException{

    public HighFlipException(String message) {
        super(message);
    }

    public HighFlipException(String message, Throwable cause) {
        super(message, cause);
    }
}
