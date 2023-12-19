package com.tinydb.annotation;

import com.alibaba.fastjson.annotation.JSONField;

import java.lang.annotation.*;

/**
 * TableColumn Annotation
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface TableColumn {
    /* Column Name */
    String value();

    /* If exist */
    boolean exists() default true;
}
