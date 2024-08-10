package com.tinydb.data;

import lombok.Data;

import java.util.List;
@Data
public class Result<T> {

    private boolean success;

    private String message;

    private List<T> data;

    private int rows;

    private double duration;
}
