package com.test.question.multi_thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(5, () -> {
            System.out.println(String.format("take time: %sms",
                    System.currentTimeMillis() - start));
            System.out.println("CyclicBarrier finished.");
        });
        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread()
                            .getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
