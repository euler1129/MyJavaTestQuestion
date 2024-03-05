package com.test.question.multi_thread;

import java.util.concurrent.*;

public class SetDefaultUncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        ThreadFactory factory = (Runnable r) -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((Thread thread, Throwable e) -> {
                System.out.println("error..." + e.getMessage());
            });
            return t;
        };
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                factory);
        executorService.execute(new Task());
    }
    private static class Task implements Runnable {
        @Override
        public void run() {
            int i = 1 / 0;
        }
    }
}
