package com.bank.service;

import com.bank.pojo.AccountUser;

public class TakeMoneyCallback extends BankTaskCallback {
    private AccountUser accountUser;

    private long money;

    public TakeMoneyCallback(AccountUser accountUser, long money) {
        this.accountUser = accountUser;
        this.money = money;
    }

    @Override
    public void callbackFun() {
        accountUser.lock.lock();
        try {
            if (accountUser.getMoney() >= money) {
                accountUser.setMoney(accountUser.getMoney() - money);
                //更新数据库
                updateDb(accountUser);
                System.out.println(accountUser.getName() + "save money:" + this.money + " sucess!");
            } else {
                System.out.println(accountUser.getName() + "take money:" + this.money + " failed! not enough money!");
            }
        } finally {
            accountUser.lock.unlock();
        }
    }
}
