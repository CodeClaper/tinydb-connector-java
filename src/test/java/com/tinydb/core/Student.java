package com.tinydb.core;
import com.tinydb.annotation.TableColumn;
import com.tinydb.annotation.TableId;
import com.tinydb.annotation.TableName;
import lombok.Data;

/**
 * This is an entity mapping table student.
 */
@Data
@TableName(value = "Student")
public class Student {

    @TableId
    private String id;

    @TableColumn(value = "name")
    private String name;

    @TableColumn(value = "age")
    private int age;

    @TableColumn(value = "sex")
    private char sex;

    /* Notice: `class` is keyword in java, so we use `class` as substitute */
    @TableColumn(value = "class")
    private Class clazz;

}
