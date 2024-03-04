package com.test.question.multi_thread;

public class YieldTest {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(
                        Thread.currentThread().getName() + "-" + i);
                if (i % 2 == 0) {
                    Thread.yield();
                }
            }
        };
        Thread t1 = new Thread(runnable, "老王");
        Thread t2 = new Thread(runnable, "小蜜");
        //t1.setPriority(Thread.MIN_PRIORITY);
        //t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
