package com.tinydb.core;

import com.tinydb.annotation.TableColumn;
import lombok.Data;

@Data
public class B {

    @TableColumn(value = "id")
    private String id;

    @TableColumn(value = "name")
    private String name;

    @TableColumn(value = "a")
    private A a;

}
