package com.bank.pojo;

import com.bank.service.SaveMoneyCallback;
import com.bank.service.TakeMoneyCallback;
import com.bank.service.TransferCallback;
import com.liucan.callback.InterfaceCaller;
import com.liucan.db.AccountDbMgr;
import com.liucan.db.model.account;
import com.liucan.threadMgr.ThreadPool;
import com.thrift.AccountResult;
import com.thrift.BankliucanQry;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<AccountUser> accountUsers;

    public Bank() {
        accountUsers = new ArrayList<AccountUser>();
        getAllAccountFromDb();
    }

    //数据读取所有信息到内存
    private void getAllAccountFromDb() {
        AccountDbMgr dbMgr = new AccountDbMgr();
        dbMgr.init();
        List<account> accounts = dbMgr.selectAllAccount();
        for (account account : accounts) {
            AccountUser accountUser = new AccountUser();
            accountUser.setAccountId(account.getAccountid());
            accountUser.setMoney(account.getMoney());
            accountUser.setName(account.getName());
            accountUser.setSex(account.getSex());
            accountUser.setUid(account.getUid());
            add(accountUser);
        }
        dbMgr.close();
    }

    /*
     * 通过thrift来通过账号id查询账号余额
     */
    public int getMoney(int accountId) {
        int money = -1;
        TTransport transport = null;
        try {
            System.out.println("开始查询账号余额...........");
            transport = new TSocket("127.0.0.1", 30002, 15000);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            BankliucanQry.Client client = new BankliucanQry.Client(protocol);
            transport.open();

            AccountResult result = client.serachMoneyByAccountId(accountId);
            money = Integer.valueOf(result.msg);
            System.out.println("查询到账号："+ accountId + "的余额为："+result.msg);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
        return money;
    }

    //存钱
    public boolean saveMoney(int accountId, long money) {
        boolean result = false;
        AccountUser accountUser = getAccountUser(accountId);
        if (money > 0 && accountUser != null) {
            result = true;
            InterfaceCaller caller = new InterfaceCaller();
            caller.setCallback(new SaveMoneyCallback(accountUser, money));
            ThreadPool.getInstance().addTask(caller);
        }
        return result;
    }

    //取钱
    public boolean takeMoney(int accountId, long money) {
        boolean result = false;
        AccountUser accountUser = getAccountUser(accountId);
        if (money > 0 && accountUser != null) {
            result = true;
            InterfaceCaller caller = new InterfaceCaller();
            caller.setCallback(new TakeMoneyCallback(accountUser, money));
            ThreadPool.getInstance().addTask(caller);
        }
        return result;
    }

    //转账
    public boolean transferMoney(int accountIdFrom, int accountIdTo, long money) {
        boolean result = false;
        AccountUser from = getAccountUser(accountIdFrom);
        AccountUser to = getAccountUser(accountIdTo);
        if (from != null && to != null && money > 0) {
            result = true;
            InterfaceCaller caller = new InterfaceCaller();
            caller.setCallback(new TransferCallback(from, to, money));
            ThreadPool.getInstance().addTask(caller);
        }
        return result;
    }

    private void add(AccountUser accountUser) {
        accountUsers.add(accountUser);
    }

    private AccountUser getAccountUser(int accountId) {
        AccountUser result = null;
        for (AccountUser user : accountUsers) {
            if (accountId == user.getAccountId()) {
                result = user;
                break;
            }
        }
        return result;
    }

    public List<AccountUser> getAccounts() {
        return accountUsers;
    }
}
