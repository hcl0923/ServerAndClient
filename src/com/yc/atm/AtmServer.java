package com.yc.atm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: testNet
 * @description:
 * @author: 作者
 * @create: 2021-02-05 14:01
 */
public class AtmServer {
    static Boolean flag=true;

    public static void main(String[] args) throws IOException {
        Bank b = new Bank();
        ServerSocket ss = new ServerSocket(8887);
        System.out.println("银行服务器启动....监听:"+ss.getLocalPort()+"端口");

        //还有ServerSocket监听13001
        //接客户端请求-> tomcat: 8080 8009
        Notify n=new Notify(){
            @Override
            public synchronized void notifyResult(boolean f) {
                flag=f;
            }
        };
        Thread th = new Monitior(n,ss);
        th.start();

        while (flag) {
            try {
                Socket s = ss.accept();//close()->Exception
                System.out.println("有atm客户端" + s.getRemoteSocketAddress() + "连接。。。");
                Thread t = new Thread(new BankService(s, b));
                t.setDaemon(true);
                t.start();
            } catch (Exception e) {
                
            }
        }
        System.out.println("服务器正常关闭");
    }
}
