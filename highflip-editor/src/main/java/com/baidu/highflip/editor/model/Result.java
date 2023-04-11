package com.baidu.highflip.editor.model;

/**
 * customized response of controller
 */
public class Result {
    /**
     * similar to http status code
     */
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.code = 200;
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.code = 200;
        result.data = data;
        return result;
    }

    public static Result failure(int errorCode, String message) {
        Result result = new Result();
        result.code = errorCode;
        result.message = message;
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
