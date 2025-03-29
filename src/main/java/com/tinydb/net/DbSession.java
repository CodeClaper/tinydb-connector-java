package com.tinydb.net;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;
import com.tinydb.utils.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class DbSession implements Session {

    private final Socket socket;

    /* The flag of session ending. */
    private static final String OVER_FLAG = "OVER";

    public DbSession(String host, int port) throws TinyDbConnectException {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new TinyDbConnectException(e.getMessage());
        }
    }

    @Override
    public boolean login(String account, String password) {
        String loginInfo = String.format("%s/%s", account, password);
        try {
            OutputStream out = this.socket.getOutputStream();
            /* Send */
            out.write(loginInfo.getBytes(StandardCharsets.UTF_8));
            out.flush();
            /* Receive */
            InputStream in = socket.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[65535];
            int len =  in.read(buf);
            Assert.isTrue(len <= 65535, "Overflow.");
            String resp = new String(buf, StandardCharsets.UTF_8).trim();
            System.out.println(resp);
            return !"No access.".equals(resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int solveInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order();
        return buffer.getInt();
    }

    public String sendAndRec(String sql) {
        try {
            OutputStream out = this.socket.getOutputStream();
            /* Send */
            out.write(sql.getBytes(StandardCharsets.UTF_8));
            out.flush();
            /* Receive */
            InputStream in = socket.getInputStream();
            StringBuilder sb = new StringBuilder();

            while (true) {
                int len, rlen, total = 0;
                byte[] sbuf = new byte[4];
                if (in.read(sbuf, 0, 4) == -1)
                    throw new TinyDbConnectException("Not receive any data.");
                len = this.solveInt(sbuf);
                Assert.isTrue(len > 0, "Not receive any data.");
                ByteBuffer byteBuffer = ByteBuffer.allocate(len);
                byte[] buf = new byte[65535];
                while(total < len) {
                    rlen = in.read(buf, 0, len);
                    if (rlen == - 1)
                        throw new TinyDbConnectException("Not receive any data.");
                    byteBuffer.put(buf, total, rlen);
                    total += rlen;
                }
                String line = new String(byteBuffer.array(), StandardCharsets.UTF_8).trim();
                /* If meeting over flag, session stop. */
                if (OVER_FLAG.equals(line))
                    break;
                System.out.println(line);
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws TinyDbIOException {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new TinyDbIOException(e.getMessage());
        }
    }

}
