package com.tinydb.mapping;

import com.tinydb.data.Result;

import java.util.List;

public interface Mapping {
    Result<T> mapping(String text, Class<T> clazz);
}
