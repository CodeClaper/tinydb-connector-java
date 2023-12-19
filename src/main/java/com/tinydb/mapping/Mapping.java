package com.tinydb.mapping;

import java.util.List;

public interface Mapping {
    <T> T mapping(String text, Class<T> clazz);

    <T> List<T> batchMapping(String text, Class<T> clazz);
}
