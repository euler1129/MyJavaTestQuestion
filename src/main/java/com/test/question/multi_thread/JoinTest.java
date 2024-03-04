package com.test.question.multi_thread;

public class JoinTest {
    public static void main(String[] args) throws Exception {
        System.out.println("start");
        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
        System.out.println("end");
    }
}
