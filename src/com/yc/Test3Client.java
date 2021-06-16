package com.yc;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: testNet
 * @description:
 * @author: 作者
 * @create: 2021-01-25 11:48
 */
public class Test3Client {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket(InetAddress.getLocalHost(),9999);
        InputStream is=s.getInputStream();
        DataInputStream dis=new DataInputStream(is);

        String ss=dis.readUTF();
        System.out.println("服务器"+s.getRemoteSocketAddress()+"对客户端"+s.getLocalSocketAddress()+"说"+ss);
        dis.close();
        is.close();
        s.close();
    }
}
