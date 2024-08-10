package com.tinydb.core;

import com.tinydb.annotation.TableColumn;
import com.tinydb.annotation.TableName;

@TableName(value = "Teacher")
public class Teacher {

    private String id;

    private String name;

    private char sex;

    private int age;

    private String subject;

    private String phone;
}
