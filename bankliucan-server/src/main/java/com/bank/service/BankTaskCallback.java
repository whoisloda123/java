package com.bank.service;

import com.bank.pojo.AccountUser;
import com.liucan.callback.InterfaceCallback;
import com.liucan.db.AccountDbMgr;
import com.liucan.db.model.account;

public class BankTaskCallback implements InterfaceCallback {
    @Override
    public void callbackFun() {

    }

    protected void updateDb(AccountUser accountUser) {
        AccountDbMgr dbMgr = new AccountDbMgr();
        dbMgr.init();

        account user = new account();
        user.setAccountid(Integer.valueOf(String.valueOf(accountUser.getAccountId())));
        user.setUid(Integer.valueOf(String.valueOf(accountUser.getUid())));
        user.setSex(accountUser.getSex());
        user.setName(accountUser.getName());
        user.setMoney(Integer.valueOf(String.valueOf(accountUser.getMoney())));

        dbMgr.updateByPrimaryKey(user);
        dbMgr.close();
    }
}
