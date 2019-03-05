package com.liucan.multthread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 接口：ExecutorService，ScheduledExecutorService
 * <p>
 * ThreadPoolExecutor是ExecutorService的实现者，就是线程池的实现
 * ScheduledThreadPoolExecutor是ScheduledExecutorService的实现者，延迟执行
 * Executors可以很方便的创建ExecutorService和ScheduledExecutorService
 *
 * @author liucan
 * @version 19-3-4
 */
public class Executor1 {

    public void test() {
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
