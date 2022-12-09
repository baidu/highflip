package com.baidu.highflip.core.utils;

import org.springframework.data.util.Optionals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class ProtoUtils {


    public static <T, V> T setOptional(T builder, String field, Optional<V> value) {
        try {
            if (value.isEmpty()) {
                Method cleanMethod = builder.getClass().getDeclaredMethod("clear" + field);
                cleanMethod.invoke(builder);
            } else {
                Class<?> clazz = value.get().getClass();
                Object val = value.get();

                Method setMethod = builder.getClass().getDeclaredMethod("set" + field, clazz);
                setMethod.invoke(builder, val);
            }
            return builder;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, V> T setOptional(T builder, String field, Supplier<V> func) {
        try {
            V value = func.get();
            setOptional(builder, field, Optional.of(value));
        } catch (Exception e) {
            setOptional(builder, field, Optional.empty());
        }
        return builder;
    }
}
