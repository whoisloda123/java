package com.thrift.query;

import com.liucan.db.AccountDbMgr;
import com.liucan.db.model.account;
import com.thrift.AccountResult;
import com.thrift.BankliucanQry;
import org.apache.thrift.TException;

public class BankliucanQryImp implements BankliucanQry.Iface {
    public AccountResult serachMoneyByAccountId(int accountId) throws TException {
        AccountResult accountRes = new AccountResult();
        AccountDbMgr dbMgr = new AccountDbMgr();
        dbMgr.init();

        account account = dbMgr.selectByPrimaryKey(accountId);
        accountRes.code = 1;
        accountRes.msg = account.getMoney().toString();

        return accountRes;
    }
}
