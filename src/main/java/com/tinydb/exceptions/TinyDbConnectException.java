package com.tinydb.exceptions;

import java.net.ConnectException;

/**
 * TinyDb Connect Exception.
 */
public class TinyDbConnectException extends ConnectException {

    public TinyDbConnectException() {
        this("TinyDb Connection exception.");
    }
    public TinyDbConnectException(String msg) {
        super(msg);
    }

}
