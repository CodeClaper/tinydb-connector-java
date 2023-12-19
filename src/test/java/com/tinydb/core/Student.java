package com.tinydb.core;
import com.tinydb.annotation.TableColumn;
import com.tinydb.annotation.TableId;
import com.tinydb.annotation.TableName;
import lombok.Data;

/**
 * This is an entity mapping table student.
 */
@Data
@TableName(value = "student")
public class Student {

    @TableId
    private String id;

    @TableColumn(value = "name")
    private String name;

    /* Notice: `class` is keyword in java, so we use `class` as substitute */
    @TableColumn(value = "class")
    private String clazz;

    @TableColumn(value = "male")
    private char male;
}
