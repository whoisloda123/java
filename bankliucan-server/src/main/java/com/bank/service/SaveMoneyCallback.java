package com.bank.service;

import com.bank.pojo.AccountUser;

public class SaveMoneyCallback extends BankTaskCallback {
    private AccountUser accountUser;

    private long money;

    public SaveMoneyCallback(AccountUser accountUser, long money) {
        this.accountUser = accountUser;
        this.money = money;
    }

    @Override
    public void callbackFun() {
        accountUser.lock.lock();
        try {
            accountUser.setMoney(accountUser.getMoney() + money);
            //更新数据库
            updateDb(accountUser);
            System.out.println(accountUser.getName() + "save money:" + this.money + " sucess!");
        } finally {
            accountUser.lock.unlock();
        }
    }
}
