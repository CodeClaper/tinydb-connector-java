package com.tinydb.core;

import com.tinydb.annotation.TableName;

@TableName(value = "Class")
public class Class {

    private String id;

    private String location;

    private Teacher master;
}
