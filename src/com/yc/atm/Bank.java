package com.yc.atm;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: testNet
 * @description:银行
 * @author: 作者
 * @create: 2021-02-05 13:54
 */
public class Bank {
    //联接数据库的操作
    private List<BankAccount> bas = new ArrayList<BankAccount>();
    public Bank() {
        for (int i = 1;i<10; i++) {
            bas.add(new BankAccount(i,10));
        }
    }
    //查询
    public BankAccount search(int id) {
        BankAccount ba = null;
        for (BankAccount t : bas) {
            if (t.getId() == id) {
                ba = t;
                break;
            }
        }
        if (ba == null) {
            throw new RuntimeException("bankaccount " + id + " does not exists");
        }
        return ba;
    }
    public synchronized BankAccount deposite(int id, double money) {
        BankAccount ba = search(id);
        synchronized(this) {
            ba.setBalance(ba.getBalance() + money);
        }
        return ba;
    }
    public synchronized BankAccount withdraw(int id,double money){
        BankAccount ba = search(id);
        if (ba.getBalance() < money) {
            throw new RuntimeException("bankaccount " + id + " does not have enought money");
        }
        synchronized (this) {
            ba.setBalance(ba.getBalance() - money);
        }
        return ba;
    }

}
