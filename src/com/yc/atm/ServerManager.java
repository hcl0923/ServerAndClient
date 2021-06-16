package com.yc.atm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: testNet
 * @description:
 * @author: 作者
 * @create: 2021-02-05 13:44
 */
public class ServerManager {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",9999);
        PrintWriter pw = new PrintWriter(s.getOutputStream() );
        pw.println("STOP");
        pw.flush();
        s.close();
        System.out.println("客户端断开与服务器的联接...." );
    }
}
