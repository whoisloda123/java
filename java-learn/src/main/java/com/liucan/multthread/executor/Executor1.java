package com.liucan.multthread.executor;

import java.util.concurrent.*;

/**
 * 一.接口：ExecutorService，ScheduledExecutorService
 *  1.ThreadPoolExecutor是ExecutorService的实现者，就是线程池的实现
 *  2.ScheduledThreadPoolExecutor是ScheduledExecutorService的实现者，延迟执行
 *  3.Executors可以很方便的创建ExecutorService和ScheduledExecutorService
 *
 * 二.ThreadPoolExecutor里面的4种拒绝策略
 *  场景,当一个任务通过execute(Runnable)方法欲添加到线程池时：
 *      a.如果线程数量小于corePoolSize,即使里面的线程都处于空闲，也会创建新线程
 *      b.如果线程数量等于corePoolSize,如果缓冲列表未满，任务会被放入队列里面
 *      c.如果线程数量大于corePoolSize,缓冲队列已满，且线程数量小于maximumPoolSize，创建新线程
 *      d.如果线程数量大于corePoolSize,缓冲队列已满，且线程数量等于maximumPoolSize，会使用拒绝策略来处理此任务
 *  也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务
 *  当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数
 *
 *  1.AbortPolicy:抛出RejectedExecutionException异常：默认
 *  2.CallerRunsPolicy：由当前调用者的线程执行
 *  3.DiscardOldestPolicy：抛弃最早进入队列里面的任务
 *  4.DiscardPolicy：抛弃多余的任务
 *
 * @author liucan
 * @version 19-3-4
 */
public class Executor1 {

    private void rejectExecutionPolicy(RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                10,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                rejectedExecutionHandler);

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + " is running"));
        }

        threadPoolExecutor.shutdown();
    }

    public void test() {
        rejectExecutionPolicy(new ThreadPoolExecutor.AbortPolicy());
        rejectExecutionPolicy(new ThreadPoolExecutor.CallerRunsPolicy());
        rejectExecutionPolicy(new ThreadPoolExecutor.DiscardOldestPolicy());
        rejectExecutionPolicy(new ThreadPoolExecutor.DiscardPolicy());

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            executorService.submit(() -> "1").get();

            //并不会马上关闭，而是不接受新的任务，等所有正在执行的任务结束后，然后关闭
            executorService.shutdown();
            //马上关闭，即使有正在执行的任务，返回从未执行的任务列表
            executorService.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
