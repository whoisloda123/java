package com.liucan.multthread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore可控制同时执行的线程数
 * Semaphore可以控制某个资源可被同时访问的个数
 *
 * @author liucan
 * @version 19-3-4
 */
public class Semaphore1 {

    public void test() {
        String[] strings = {"1", "2", "3"};
        Semaphore semaphore = new Semaphore(2);
        new Thread(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println(strings[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println(strings[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(strings[2]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }).start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
