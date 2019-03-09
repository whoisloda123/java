package com.liucan.multthread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * 后面把例子加上
 *
 * @author liucan
 * @version 19-3-5
 */
public class Lock1 {
    //可重入锁
    private final Lock reentrantLock = new ReentrantLock();
    //条件变量
    private final Condition condition = reentrantLock.newCondition();

    //读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    //自旋锁
    private final Lock spinLock = new SpinLock();

    public void test() {
        try {
            readLock.lock();
            writeLock.lock();

            spinLock.tryLock(5, TimeUnit.SECONDS);
            reentrantLock.lock();

            condition.await();
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
            spinLock.unlock();
            reentrantLock.unlock();
            readLock.unlock();
            writeLock.unlock();
        }
    }
}
