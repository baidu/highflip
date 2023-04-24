package com.baidu.highflip.editor.model;

/**
 * customized response of controller
 */
public class Result<T> {
    /**
     * similar to http status code
     */
    private int code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.code = 200;
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result result = new Result<T>();
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

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
