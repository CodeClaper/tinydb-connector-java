package com.tinydb.mapping;

import java.util.List;

public interface Mapping<T> {
    List<T> mapping(String text, Class<T> clazz);
}
