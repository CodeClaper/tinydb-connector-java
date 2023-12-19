package com.tinydb.exceptions;

import java.io.IOException;

/**
 * TinyDB IO Exception.
 */
public class TinyDbIOException extends IOException {

    public TinyDbIOException() {
        this("TinyDb IO exception.");
    }

    public TinyDbIOException(String msg) {
        super(msg);
    }
}
