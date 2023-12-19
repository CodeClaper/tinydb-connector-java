package com.tinydb.annotation;

import java.lang.annotation.*;

/**
 * TableId annotation.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableId {
}
