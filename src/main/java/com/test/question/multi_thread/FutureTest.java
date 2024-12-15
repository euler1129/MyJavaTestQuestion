package com.test.question.multi_thread;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 创建一个Callable任务
        Callable<Integer> task = () -> {
            System.out.println("计算开始...");
            TimeUnit.SECONDS.sleep(2);//模拟长时间的计算
            return 188*199*321;
        };
        // 提交任务给线程池，并获取Future对象
        Future<Integer> future = executor.submit(task);
        try {
            // 阻塞获取任务的结果
            Integer result = future.get();
            System.out.println("计算结果：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
