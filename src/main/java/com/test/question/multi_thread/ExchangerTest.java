package com.test.question.multi_thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {
    private static void simpleDataExchange(){
        Exchanger exchanger = new Exchanger();
        new Thread(() -> {
            Object data = "AAA";
            System.out.println(Thread.currentThread().getName() + data);
            // 开始交换数据
            try {
                data = exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + data);
        }).start();
        new Thread(() -> {
            Object data = "BBB";
            System.out.println(Thread.currentThread().getName() + data);
            // 开始交换数据
            try {
                data = exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + data);
        }).start();
    }
    private static void timeoutDataExchange(){
        Exchanger exchanger = new Exchanger();
        new Thread(() -> {
            Object data = "AAA";
            System.out.println(Thread.currentThread().getName() + data);
            // 开始交换数据
            try {
                data = exchanger.exchange(data,
                        3000L,
                        TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + data);
        }).start();
    }
    private static void interruptDataExchange()
            throws InterruptedException {
        Exchanger exchanger = new Exchanger();
        Thread thread = new Thread(() -> {
            Object data = "AAA";
            System.out.println(Thread.currentThread().getName() + data);
            // 开始交换数据
            try {
                data = exchanger.exchange(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + data);
        });
        thread.start();
        // 线程中断
        Thread.sleep(3000L);
        thread.interrupt();
    }
    private static void pairwiseDataExchange(){
        Exchanger exchanger = new Exchanger();
        for (int i = 1; i <= 10; i++){
            Integer data = i;
            new Thread(() -> {
                // 开始交换数据
                try {
                    Object exchange = exchanger.exchange(data);
                    System.out.println(Thread.currentThread().getName()
                            + "-" + exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread" + i).start();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //simpleDataExchange();
        //timeoutDataExchange();
        //interruptDataExchange();
        pairwiseDataExchange();
    }
}
