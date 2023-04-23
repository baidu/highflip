package com.baidu.highflip.editor.web.aop;

import org.aspectj.lang.annotation.Pointcut;

public class RequestAspect {

    protected static final String CONTROLLER_METHOD_NAME = "controllerMethod()";

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    protected void controllerMethod() {

    }
}