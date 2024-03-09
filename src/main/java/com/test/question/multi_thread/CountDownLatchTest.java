package com.test.question.multi_thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args)
            throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(100);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++){
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
            }).start();
        }
        cdl.await();
        System.out.println(cdl.getCount());
        System.out.println(String.format("take time: %sms",
                System.currentTimeMillis() - start));
    }
}
