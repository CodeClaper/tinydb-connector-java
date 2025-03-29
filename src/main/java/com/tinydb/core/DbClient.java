package com.tinydb.core;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;
import com.tinydb.net.Session;
import com.tinydb.net.DbSession;


public class DbClient {

    private final String host;
    private final int port;


    public DbClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean login(String account, String password) {
        String ret = this.exec(String.format("%s/%s", account, password));
        System.out.println(ret);
        return !ret.equals("No access.");
    }

    /**
     * select batch data
     *
     * @param sql sql to execute
     * @return  batch data
     */
    public String exec(String sql) {
        try (Session session = new DbSession(this.host, this.port)) {
            return session.sendAndRec(sql);
        } catch (TinyDbConnectException | TinyDbIOException e) {
            throw new RuntimeException(e);
        }
    }
}
