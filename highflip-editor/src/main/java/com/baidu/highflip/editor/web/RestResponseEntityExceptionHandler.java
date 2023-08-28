package com.baidu.highflip.editor.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.baidu.highflip.editor.model.HighFlipEditorException;
import com.baidu.highflip.editor.model.Result;

/**
 * global exception handler
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(
            RuntimeException e, WebRequest request) {
        Result result = Result.failure(400, e.getMessage());
        return handleExceptionInternal(e, result, new HttpHeaders(),
                                       HttpStatus.INTERNAL_SERVER_ERROR,
                                       request);
    }

    @ExceptionHandler(value = {HighFlipEditorException.class})
    protected ResponseEntity<Object> handleDsException(
            HighFlipEditorException e, WebRequest request) {
        Result result = Result.failure(e.getCode(), e.getMessage());
        return handleExceptionInternal(e, result, new HttpHeaders(),
                                       HttpStatus.OK, request);
    }
}


