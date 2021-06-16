package com.yc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @program: testNet
 * @description:
 * @author: 作者
 * @create: 2021-01-28 00:18
 */
public class Test9UDPServer {
    public static void main(String args[]) throws IOException {
        byte buf[] = new byte[2];//完节数组.用于缓冲数据
        DatagramPacket dp = new DatagramPacket(buf, buf.length);//构建一个数据包对象
        DatagramSocket ds = new DatagramSocket(3333);//构建一个数据包socket,占用URL的9999端口
        boolean flag = true;
        while (flag) {
            //阻塞式方法
            ds.receive(dp);//用这个9999端口来接收数据.存在dp数据包对象中的buf缓冲空间中
            System.out.println(dp.getLength());
            System.out.println(new String(buf, 0, dp.getLength()));//将字节数组转成字符串输出
        }
        ds.close();
    }
}