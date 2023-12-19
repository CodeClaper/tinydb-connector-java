package com.tinydb.net;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;

import java.io.Closeable;

public interface Session extends Closeable {
    void startTransaction();
    void endTransaction();
    void rollbackTransaction();
    String sendAndRec(String msg);

    void close() throws TinyDbIOException;
}
