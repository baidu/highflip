package com.baidu.highflip.editor.model;

public class HighFlipEditorException extends RuntimeException {

    private int code;
    private String message;

    public HighFlipEditorException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public HighFlipEditorException(String message) {
        super(message);
        this.code = 400;
        this.message = message;
    }

    public HighFlipEditorException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
