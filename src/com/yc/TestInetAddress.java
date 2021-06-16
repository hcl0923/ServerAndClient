package com.yc;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @program: testNet
 * @description: InetAddress
 *               根据命令行传入的参数来取出地址
 *               记录参数，则显示参数对应的地址，没有则本机
 * @author: 作者
 * @create: 2021-01-25 11:14
 */
public class TestInetAddress {
    public static void main(String[] args)throws UnknownHostException {
        if(args.length>0){
            String host=args[0];
            InetAddress[] addresses=InetAddress.getAllByName(host);
            for(InetAddress a:addresses){
                System.out.println(a);
            }
        }else{
            InetAddress localHostAddress=InetAddress.getLocalHost();//本机地址
            System.out.println(localHostAddress);
        }
    }
}
