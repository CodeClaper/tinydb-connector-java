package com.tinydb.net;

import com.tinydb.exceptions.TinyDbIOException;

import java.io.Closeable;

public interface Session extends Closeable {

    boolean login(String account, String password);

    String sendAndRec(String msg);

    void close() throws TinyDbIOException;
}
