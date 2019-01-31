package com.liucan.thread;

import org.springframework.stereotype.Component;

/**
 * 参考：https://blog.csdn.net/zsm2015/article/details/79553593
 * <p>
 * 一.线程：用户线程，守护线程（daemon线程）
 * 1.用户线程全部退出了，daemon线程也会自动退出
 * 2.thread.setDaemon(true)必须在thread.start()之前设置
 * 3.在Daemon线程中产生的新线程也是Daemon的
 * 4.守护线程应该永远不去访问固有资源，如文件、数据库，因为它会在任何时候甚至在一个操作的中间发生中断
 * <p>
 * 二.sleep和yield区别：https://www.cnblogs.com/hypnotizer/p/5579095.html
 * sleep线程处于阻塞状态，让出cpu时间片给其他线程执行，结束后才会转入就绪状态
 * yield是暂停线程，不会阻塞线程，让线程运行状态转入就绪状态，线程调度器可以继续执行
 * <p>
 * 三.suspend()、resume()和stop()已经过期不建议使用，因为调用后不会释放资源，容易造成死锁问题，改为用sleep和notify等待通知机制
 * <p>
 * 四.volatile 参考：https://www.cnblogs.com/chengxiao/p/6528109.html
 * a.多线程同时访问一个对象时（主内存），每个执行的线程可以拥有该对象的一份拷贝（本地内存），而本地内存的操作会在一个时机同步到主内存
 * 所以程序在执行过程中，一个线程看到的变量并不一定是最新的
 * b.轻量级锁
 * c.能保证共享变量对所有线程的可见性，当写一个volatile变量时，JMM会把该线程对应的本地内存中的变量强制刷新到主内存中去
 * 保证所有线程对该变量的可见性
 * d.禁止指令重排序优化
 * e.volatile对于单个的共享变量的读/写具有原子性，但是像num++这种复合操作，volatile无法保证其原子性,可以用原子锁
 *
 * 五.synchronized
 *  参考：https://www.cnblogs.com/paddix/p/5367116.html
 *  1.synchronized锁定是一个对象，其他试图访问该对象synchronized方法会被锁住,而每一个对象都可以做为一个锁（Monitor锁）
 *  2.在普通方法前面，锁的是当前实例对象（其他的synchronized标志的方法也会被锁住,非synchronized的不会被锁住）
 *  3.在静态方法前面，锁的是整个类
 *  4.在方法块里面synchronized(object),锁的是括号里面的对象
 *  5.实现机制
 *      a.每个对象有个监视器锁（Monitor锁）
 *      b.Monitor被占用的时候其他线程会阻塞，进入执行命令MonitorEnter获取Monitor,退出执行MonitorExit释放Monitor
 *      c.notify/notifyAll和wait方法都依赖Monitor锁
 *      d.synchronized方法，和方法块是基本Monitor锁实现，执行时候进入获取锁，离开释放锁
 *      e.所以notify/notifyAll和wait方法都必须位于synchronized内，否者抛异常
 *
 * @author liucan
 * @version 19-1-20
 */
@Component
public class Thread1 {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.example();
    }

    public void example() {
        Thread thread = new Thread(() -> System.out.println(1));
        thread.setDaemon(true); //必须在启动前调用
        thread.setName("线程");
        thread.start();

        Print print = new Print();
//        new Thread(print, "C").start();
//        new Thread(print, "A").start();
//        new Thread(print, "B").start();

        new Thread(() -> print.method1()).start();
        new Thread(() -> print.method5()).start();
        new Thread(() -> print.method2()).start();
//        new Thread(() -> print.method3()).start();
//        new Thread(() -> print.method4()).start();
    }

    /**
     * 设计一个程序，启动三个线程A,B,C,各个线程只打印特定的字母，各打印10次，例如A线程只打印‘A’。要求在控制台依次显示“ABCABC…”
     * 下面的有点问题
     */
    private final Object lock = new Object();

    private class Print implements Runnable {

        private String currentPrint = "A";

        //给线程执行的
        @Override
        public void run() {
            synchronized (lock) {
                String threadName = Thread.currentThread().getName();
                if (threadName.equals(currentPrint)) {
                    System.out.println(currentPrint);
                    if (currentPrint.equals("A")) {
                        currentPrint = "B";
                    } else if (currentPrint.equals("B")) {
                        currentPrint = "C";
                    }
                    lock.notifyAll();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * synchronized锁的是
         */
        private synchronized void method1() {
            try {
                Thread.sleep(3000);
                System.out.println("method1" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private synchronized void method2() {
            try {
                Thread.sleep(1000);
                System.out.println("method2" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void method5() {
            System.out.println("method5" + Thread.currentThread().getName());
        }

        private void method3() {
            try {
                synchronized (this) {
                    Thread.sleep(3000);
                    System.out.println("method3" + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void method4() {
            try {
                synchronized (this) {
                    Thread.sleep(1000);
                    System.out.println("method4" + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
