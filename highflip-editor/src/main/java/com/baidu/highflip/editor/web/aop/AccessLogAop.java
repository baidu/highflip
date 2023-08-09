package com.baidu.highflip.editor.web.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baidu.highflip.editor.utils.JacksonUtils;
import com.baidu.highflip.editor.web.annotation.IgnoreRequestBody;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

@Aspect
@Component
@Order(0)
public class AccessLogAop extends RequestAspect {

    private static final String LOG_CONTENT =
            "RequestMethod = {}, RequestUri = {}, RemoteAddress = {}, " +
            "RequestParams = `{}`, RequestBody = {}";
    // when request method is post or put, log request body
    private static final Set<String> REQUEST_BODY_METHOD =
            ImmutableSet.of(RequestMethod.POST.name(),
                            RequestMethod.PUT.name());

    private Map<Class, Logger> loggerHolder = Maps.newConcurrentMap();

    @Before(CONTROLLER_METHOD_NAME)
    public void before(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Object[] args = joinPoint.getArgs();

        log(targetClass, method, args);
    }

    private void log(Class targetClass, Method method, Object[] args) {
        // 1. get logger
        Logger logger = loggerHolder.putIfAbsent(
                targetClass, LoggerFactory.getLogger(targetClass));
        logger = logger == null ? loggerHolder.get(targetClass) : logger;

        // 2. get request
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 3. get log content
        // request body is a method arg, that has @RequestBody annotation.
        String remoteAddress = request.getRemoteAddr();
        String url = request.getRequestURI();
        String requestParams = request.getQueryString();
        Object body = null;
        if (REQUEST_BODY_METHOD.stream()
                               .anyMatch(m -> m.equals(request.getMethod()))) {
            if (method.getAnnotation(IgnoreRequestBody.class) == null) {
                Annotation[][] annotations = method.getParameterAnnotations();
                for (int i = 0; i < annotations.length; i++) {
                    for (int j = 0; j < annotations[i].length; j++) {
                        if (annotations[i][j] instanceof RequestBody) {
                            body = args[i];
                        }
                    }
                }
            }
        }

        logger.info(LOG_CONTENT,
                    targetClass.getSimpleName() + "." + method.getName(),
                    url, remoteAddress, requestParams, JacksonUtils.toJson(body));
    }
}