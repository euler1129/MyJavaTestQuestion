package com.test.question.multi_thread;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) {
        // 创建一个Callable任务
        Callable<Integer> callableTask = () -> {
            System.out.println("计算任务开始...");
            TimeUnit.SECONDS.sleep(1); // 模拟耗时操作
            return 191 * 323 * 130;
        };
        // 创建一个FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(callableTask);
        // 将FutureTask提交给线程池执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);
        // 或者直接在主线程中运行任务
        // new Thread(futureTask).start();
        try {
            // 等待任务完成并获取结果
            Integer result = futureTask.get();
            System.out.println("计算结果：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
