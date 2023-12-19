package com.tinydb.core;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.exceptions.TinyDbIOException;
import com.tinydb.net.TinyDbSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class TinyDbClientTest {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 4083;

    @Test
    public void selectOne() {
        TinyDbClient client = new TinyDbClient(HOST, PORT);
        Student st = client.selectOne("select * from student where id = 'S004'", Student.class);
        Assert.assertNotNull(st);
    }

    @Test
    public void selectNest() {
        TinyDbClient client = new TinyDbClient(HOST, PORT);
        B b = client.selectOne("select * from B where id = 'S001'", B.class);
        Assert.assertNotNull(b);
    }

    @Test
    public void selectBatch() {
        TinyDbClient client = new TinyDbClient(HOST, PORT);
        List<Student> sts = client.selectBatch("select * from student", Student.class);
        Assert.assertFalse(sts.isEmpty());
    }

    @Test
    public void batchInsert() {
        String[] names = {"zhangsan", "lisi", "wangwu", "liuliu", "bemki", "Bugud"};
        String[] addressList = {"beijing", "shanghai", "shenzhen", "ningbo", "xian",  "jinan", "qingdao", "guangzhou", "chongqing" };
        try (TinyDbSession session = new TinyDbSession(HOST, PORT)) {
           for (int i = 0; i <= 100000; i++) {
               String name = names[i % names.length];
               String address = addressList[i % addressList.length];
               String sql = String.format("insert into test values('%d', '%s', '%s')", i, name, address);
               System.out.println(i + "=====>" + session.sendAndRec(sql));
           }
       } catch (TinyDbConnectException | TinyDbIOException e) {
           throw new RuntimeException(e);
       }
    }
}