package com.tinydb;

import com.tinydb.exceptions.TinyDbConnectException;
import com.tinydb.net.DbSession;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TinyDbConnectException {
        Scanner scanner = new Scanner(System.in);
        DbSession client = new DbSession("127.0.0.1", 4083);
        System.out.println("your account > ");
        String account = scanner.nextLine();
        System.out.println("your password > ");
        String password = scanner.nextLine();
        boolean hasLogin = client.login(account, password);
        while(hasLogin) {
            System.out.println("simpledb > ");
            String sql = scanner.nextLine();
            client.sendAndRec(sql);
        }
    }
}
