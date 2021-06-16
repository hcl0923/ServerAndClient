package com.yc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: testNet
 * @description:客户端连接上服务器后，发一句问候，服务给一个带时间的回应，相当与一个回音。
 * @author: 作者
 * @create: 2021-01-25 12:07
 */
public class Test4Client {
    public static void main(String[] args)throws IOException {
        System.out.println("客户端启动");
        Socket s=new Socket(InetAddress.getLocalHost(),9991);
        System.out.println("客户端连接"+s.getRemoteSocketAddress()+"成功");
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());

        dos.writeUTF("i am client");
        dos.flush();
        System.out.println("客户端发送问候完毕");
        String ss=null;
        if((ss=dis.readUTF())!=null){
            System.out.println("服务器"+s.getRemoteSocketAddress()+"的回应:"+ss);
        }
        dis.close();
        dos.close();
        s.close();
        System.out.println("客户端正常关闭");
    }
}
