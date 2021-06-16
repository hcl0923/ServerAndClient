package com.yc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: testNet
 * @description:服务器向客户端发送信息
 * @author: 作者
 * @create: 2021-01-25 11:43
 */
public class Test3Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(9999);
        while(true){
            Socket client=server.accept();

            OutputStream os=client.getOutputStream();
            DataOutputStream dos=new DataOutputStream(os);
            dos.writeUTF("hello"+client.getInetAddress()+":"+client.getPort());
            dos.flush();
            os.flush();
            dos.close();
            os.close();
            client.close();
        }
    }
}
