package com.tinydb.core;

import com.tinydb.annotation.TableColumn;
import lombok.Data;

@Data
public class A {
    @TableColumn(value = "id")
    private String id;
    @TableColumn(value = "name")
    private String name;
}
