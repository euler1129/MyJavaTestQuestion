package com.test.question.multi_thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 start...");
            LockSupport.park();
            System.out.println("thread1 end...");
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 start...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread1);
            System.out.println("thread2 唤醒了 thread1");
        });
        thread2.start();
    }
}
