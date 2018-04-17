package com.bank.service;

import com.bank.pojo.AccountUser;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransferCallback extends BankTaskCallback {
    private final Lock sameHashCodeLock = new ReentrantLock();

    private AccountUser from;

    private AccountUser to;

    private long money;

    public TransferCallback(AccountUser from, AccountUser to, long money) {
        this.from = from;
        this.to = to;
        this.money = money;
    }

    private void transfer() {
        String str = "transfer from:" + from.getName() + "to:" + to.getName() + ":" + money;
        if (from.getMoney() >= money) {
            from.setMoney(from.getMoney() - money);
            to.setMoney(to.getMoney() + money);

            //更新数据库
            updateDb(from);
            updateDb(to);

            System.out.println(str + " sucess!");
            System.out.println("now " + from.getName() + "money is: " + from.getMoney());
            System.out.println("now " + to.getName() + "money is: " + to.getMoney());
        } else {
            System.out.println(str + " failed! money is not enough!");
        }
    }

    @Override
    public void callbackFun() {
        //因为锁的顺序，需要注意避免死锁

        //根据对象的hash值来确定加锁顺序
        int fromHashCode = System.identityHashCode(from);
        int toHashCode = System.identityHashCode(to);

        if (fromHashCode < toHashCode) {
            from.lock.lock();
            to.lock.lock();
            try {
                transfer();
            } finally {
                to.lock.unlock();
                from.lock.unlock();
            }
        } else if (fromHashCode > toHashCode) {
            to.lock.lock();
            from.lock.lock();
            try {
                transfer();
            } finally {
                from.lock.unlock();
                to.lock.unlock();
            }
        } else { //如果hashcode一样，则另外加锁让第一个先执行完
            sameHashCodeLock.lock();
            try {
                from.lock.lock();
                to.lock.lock();
                try {
                    transfer();
                } finally {
                    to.lock.unlock();
                    from.lock.unlock();
                }
            } finally {
                sameHashCodeLock.unlock();
            }
        }
    }
}