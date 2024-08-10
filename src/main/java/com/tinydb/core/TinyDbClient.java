package com.tinydb.core;

import com.tinydb.data.Result;
import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;
import com.tinydb.mapping.FastjsonMapping;
import com.tinydb.mapping.Mapping;
import com.tinydb.net.Session;
import com.tinydb.net.TinyDbSession;
import com.tinydb.utils.Assert;

import java.util.List;

public class TinyDbClient {

    private final String host;
    private final int port;

    public TinyDbClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * select batch data
     *
     * @param sql sql to execute
     * @param clazz data class
     * @return  batch data
     * @param <T> data type
     */
    public <T> List<T> selectBatch(String sql, Class<T> clazz) {
        try (Session session = new TinyDbSession(this.host, this.port)) {
            String revc = session.sendAndRec(sql);
            Mapping mapping = new FastjsonMapping();
            Result<T> result = mapping.mapping(revc, clazz);
            Assert.isTrue(result.isSuccess(), result.getMessage());
            return result.getData();
        } catch (TinyDbConnectException | TinyDbIOException e) {
            throw new RuntimeException(e);
        }
    }
}
