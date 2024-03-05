package com.test.question.multi_thread;

public class ThreadGroupTest {
    public static void main(String[] args){
        Runnable runnable = () -> {
            System.out.println("线程组名称：" + Thread.currentThread().getThreadGroup());
            System.out.println("线程名称：" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ThreadGroup userGroup = new ThreadGroup("user");
        userGroup.setMaxPriority(Thread.MIN_PRIORITY);
        Thread userTask1 = new Thread(userGroup, runnable, "user-task1");
        Thread userTask2 = new Thread(userGroup, runnable, "user-task2");
        userTask1.start();
        userTask2.start();
        System.out.println("线程组活跃线程数：" + userGroup.activeCount());
        userGroup.list();
    }
}
