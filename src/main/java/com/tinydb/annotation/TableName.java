package com.tinydb.annotation;

import com.alibaba.fastjson.annotation.JSONField;

import java.lang.annotation.*;

/**
 * TableName Annotation
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {

    /* Table Name */
    String value();


}
