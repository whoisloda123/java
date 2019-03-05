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
    private final Lock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

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
