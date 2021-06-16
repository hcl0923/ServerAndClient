package com.yc.atm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: testNet
 * @description:ATM机存取转账的客户端
 * @author: 作者
 * @create: 2021-02-04 19:49
 */
public class AtmClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8887);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        Scanner sc = new Scanner(s.getInputStream());
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("============");
            System.out.println("1.存");
            System.out.println("2.取");
            System.out.println("3.查询");
            System.out.println("4.退出");
            System.out.println("============");
            String command = input.nextLine();
            String line;
            if ("1".equals(command)) {
                pw.println("DEPOSIT 1 100");
                pw.flush();
                line = sc.nextLine();//服务器的响应为   操作后的余额
                System.out.println("服务器的响应为:" + line);
            } else if ("2".equals(command)) {
                pw.println("WITHDRAW 1 10");
                pw.flush();
                line = sc.nextLine();//服务器的响应为   操作后的余额
                System.out.println("服务器的响应为:" + line);
            } else if ("3".equals(command)) {
                pw.println("BALANCE 1");
                pw.flush();
                line = sc.nextLine();//服务器响应为  操作后的余额
                System.out.println("服务器的响应为:" + line);
            } else {
                pw.println("QUIT");
                pw.flush();
                flag = false;
            }
        } while (flag);
    }
}
