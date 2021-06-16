package com.yc.atm;

/**
 * @program: testNet
 * @description:银行账户
 * @author: 作者
 * @create: 2021-02-05 13:50
 */
public class BankAccount {
    private int id;
    private double balance;

    public BankAccount(){
        super();
    }
    public BankAccount(int id, double balance) {
        super();
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
