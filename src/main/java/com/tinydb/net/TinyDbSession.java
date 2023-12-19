package com.tinydb.net;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class TinyDbSession implements Session {

    private final Socket socket;

    /* The flag of session ending. */
    private static final String OVER_FLAG = "OVER";

    public TinyDbSession(String host, int port) throws TinyDbConnectException {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new TinyDbConnectException(e.getMessage());
        }
    }

    public void startTransaction() {}

    public String sendAndRec(String msg) {
        try {
            OutputStream out = this.socket.getOutputStream();
            /* Send */
            out.write(msg.getBytes(StandardCharsets.UTF_8));
            out.flush();
            /* Receive */
            InputStream in = socket.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = in.read(buf)) != -1) {
                int i = 0;
                while (i < len && buf[i] != 0) { i++; }
                String line = new String(buf, 0, i,StandardCharsets.UTF_8).trim();
                /* If meeting over flag, session stop. */
                if (OVER_FLAG.equals(line))
                    break;
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void endTransaction() {}

    @Override
    public void rollbackTransaction() {

    }

    public void close() throws TinyDbIOException {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new TinyDbIOException(e.getMessage());
        }
    }

}
