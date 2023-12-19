package com.tinydb.exceptions;

import java.sql.SQLException;

/**
 * TinyDb Sql Exception.
 */
public class TinyDbSqlException extends SQLException {

    public TinyDbSqlException() {
        this("Sql exception.");
    }

    public TinyDbSqlException(String msg) {
        super(msg);
    }
}
