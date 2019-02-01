package com.liucan.threadMgr;

import com.liucan.callback.InterfaceCaller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//ConcurrentLinkedQueue; //是否可以使用这个？

//简单线程池的实现
public class ThreadPool {
    private int threadNum = 100;

    private Semaphore sem; //用于控制线程并发数

    private Lock lock = new ReentrantLock();

    private Condition emptyCond = lock.newCondition(); //队列为空条件变量

    private Condition fullCond = lock.newCondition(); //队列满时条件变量

    private CountDownLatch waitAllTheadEnd; //等待所有线程退出

    private AtomicBoolean stopFlag = new AtomicBoolean(false); //退出标志（原子锁）

    private static ThreadPool threadPool;

    private static Lock lockSingle = new ReentrantLock();

    private LimitQueue<InterfaceCaller> taskQue;

    private ThreadPool(int threadNum, int taskNum) {
        this.threadNum = threadNum;
        waitAllTheadEnd = new CountDownLatch(threadNum);
        sem = new Semaphore(taskNum);
        taskQue = new LimitQueue<>(taskNum);
    }

    //单例
    public static ThreadPool getInstance() {
        if (ThreadPool.threadPool == null) {
            lockSingle.lock();
            //保证单例唯一性
            try {
                if (ThreadPool.threadPool == null) {
                    ThreadPool.threadPool = new ThreadPool(100, 50);
                }
            } finally {
                lockSingle.unlock();
            }
        }
        return ThreadPool.threadPool;
    }

    public void start() {
        System.out.println("线程任务开始！");
        for (int i = 0; i < this.threadNum; i++) {
            new Thread(() -> {
                //执行任务
                while (!stopFlag.get()) {
                    try {
                        sem.acquire();

                        lock.lock();
                        while (!stopFlag.get() && taskQue.isEmpty()) { //队列为空
                            try {
                                emptyCond.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //取任务,执行任务
                        InterfaceCaller caller = taskQue.poll();
                        if (caller != null) {
                            caller.call();
                            fullCond.signal();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();

                        sem.release();
                    }
                }
                waitAllTheadEnd.countDown();
            }).start();
        }
    }

    public void stop() {
        try {
            stopFlag.set(true);
            lock.lock();
            try {
                emptyCond.signalAll();
                fullCond.signalAll();
            } finally {
                lock.unlock();
            }

            waitAllTheadEnd.await(); //等待所有线程退出
            System.out.println("所有线程任务已结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addTask(final InterfaceCaller caller) {
        Thread th = new Thread(() -> {
            try {
                lock.lock();
                while (!stopFlag.get() && taskQue.isFull()) { //队列满
                    try {
                        fullCond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                taskQue.offer(caller);
                emptyCond.signal();
            } finally {
                lock.unlock();
            }
        });
        th.start();

        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
