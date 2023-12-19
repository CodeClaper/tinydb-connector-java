package com.tinydb.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言
 * 用于替代org.springframework.util.Assert,
 * 因为此类断言失败抛出IllegalArgumentException, 不适合用于业务判断
 *
 * @author ibp team
 * @version V1.0
 */
public class Assert {
    /**
     * true false 判断
     * @param expression 目标对象
     * @param message 消息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }

    /**
     * true false 判断
     * @param expression 目标对象
     * @param message 消息
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new RuntimeException(message);
        }
    }

    /**
     * NULL判断
     * @param object 目标对象
     * @param message 消息
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 非NULL校验
     * @param object 目标对象
     * @param message 消息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 非空
     * @param array 目标
     * @param message 消息
     */
    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new RuntimeException(message);
        }
    }

    /**
     *
     * @param collection 目标
     * @param message 消息
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (collection.isEmpty()) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 非空
     * @param map 目标
     * @param message 消息
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (map.isEmpty()) {
            throw new RuntimeException(message);
        }
    }
}
