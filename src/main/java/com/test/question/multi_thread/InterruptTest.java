package com.test.question.multi_thread;

public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        //test3();
        test4();
    }
    private static void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("线程运行...");
                Thread.yield();
            }
        });
        thread.start();
        thread.interrupt();
    }
    private static void test2() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出...");
                    return;
                }
            }
        });
        thread.start();
        thread.interrupt();
    }
    private static void test3() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出...");
                    return;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出...");
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
    private static void test4() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断，程序退出...");
                    return;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("线程休眠被中断，程序退出...");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
