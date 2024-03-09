package com.test.question.multi_thread;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ThreadLocalTest {
    @Data
    @AllArgsConstructor
    static class User {
        private String userName;
        private int age;
    }
    private static ThreadLocal<User> t1 = ThreadLocal
            .withInitial(() -> new User("tom", 12));
    static class TestThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < 3; i++){
                User user = t1.get();
                user.setUserName(Thread.currentThread().getName());
                user.setAge(i);
                t1.set(user);
                System.out.println(t1.get());
            }
        }
    }
    public static void main(String[] args)
            throws InterruptedException {
        for (int i = 0; i < 3; i++){
            new TestThread().start();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()
                    + t1.get());
            System.out.println("-------------------");
        }
    }
}
