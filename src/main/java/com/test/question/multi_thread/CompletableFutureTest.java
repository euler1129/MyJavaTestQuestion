package com.test.question.multi_thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 异步获取用户基本信息
        CompletableFuture<String> userInfoFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("获取用户基本信息...");
            sleep(1); // 模拟网络请求或耗时操作
            return "张三";
        });
        // 异步获户账户余额
        CompletableFuture<Integer> balanceFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("获取用户账户余额...");
            sleep(1); // 模拟网络请求或耗时操作
            return 1000;
        });
        // 异步获取用户最近的交易记录
        CompletableFuture<String[]> transactionsFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("获取用户最近的交易记录...");
            sleep(3); // 模拟网络请求或耗时操作
            return new String[]{"交易1", "交易2" ,"交易3"};
        });
        // 所有异步任务完成后，合并结果并处理
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(userInfoFuture, balanceFuture, transactionsFuture)
                .thenRun(() -> {
                    try {
                        String userInfo = userInfoFuture.get();
                        Integer balance = balanceFuture.get();
                        String[] transactions = transactionsFuture.get();
                        System.out.println("所有数据获取完成！");
                        System.out.println("用户信息：" + userInfo);
                        System.out.println("账户余额：" + balance);
                        System.out.print("最近交易记录：");
                        for (String transaction : transactions) {
                            System.out.print(transaction + " ");
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
        // 等待所有操作完成
        allOfFuture.join();
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
    private static void sleep(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
