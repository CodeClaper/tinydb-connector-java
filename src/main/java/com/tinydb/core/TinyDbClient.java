package com.tinydb.core;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;
import com.tinydb.mapping.FastjsonMapping;
import com.tinydb.mapping.Mapping;
import com.tinydb.net.Session;
import com.tinydb.net.TinyDbSession;
import java.util.List;

public class TinyDbClient {

    private final String host;
    private final int port;

    public TinyDbClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * select one data.
     *
     * @param sql sql to execute
     * @param clazz data class
     * @return one data or null
     * @param <T> data type
     */
    public <T>T selectOne(String sql, Class<T> clazz) {
        try (Session session = new TinyDbSession(this.host, this.port)) {
            String data = session.sendAndRec(sql);
            Mapping mapping = new FastjsonMapping();
            return mapping.mapping(data, clazz);
        } catch (TinyDbConnectException | TinyDbIOException e) {
            throw new RuntimeException(e);
        }
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
            String data = session.sendAndRec(sql);
            Mapping mapping = new FastjsonMapping();
            return mapping.batchMapping(data, clazz);
        } catch (TinyDbConnectException | TinyDbIOException e) {
            throw new RuntimeException(e);
        }
    }
}
