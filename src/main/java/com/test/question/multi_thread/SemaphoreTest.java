package com.test.question.multi_thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()
                            + "获取许可耗时："
                            + (System.currentTimeMillis() - start));
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName()
                            + "释放许可");
                    semaphore.release();
                }
            }).start();
        }
    }
}
