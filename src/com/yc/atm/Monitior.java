package com.yc.atm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: testNet
 * @description:
 * @author: 作者
 * @create: 2021-02-05 15:16
 */
public class Monitior extends Thread {
    private Notify n;
    private ServerSocket ss1;

    public Monitior(Notify n, ServerSocket ss) {
        this.ss1=ss;
        this.n=n;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9999);
            while (true) {
                Socket s = ss.accept();
                System.out.println(s.getInetAddress() +"连接上来");
                Scanner sc = new Scanner(s.getInputStream());
                String shift;  //stop resume pause info
                if (sc.hasNext()){
                    shift = sc.nextLine();
                    if ("STOP".equals(shift)) {
                        if( n!=null){
                            n.notifyResult(false);
                        }
                        System.out.println("关闭银行服务器");
                        ss1.close();
                        break;
                    }else if ("PAUSE".equals(shift)) {
                        // flag=true ;
                        System.out.println("继续运行");
                    }else {
                        System.out.println("请重新输入");
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
