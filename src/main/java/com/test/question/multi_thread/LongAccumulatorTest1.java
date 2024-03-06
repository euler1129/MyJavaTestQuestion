package com.test.question.multi_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorTest1 {
    private static LongAccumulator longAccumulatorAdd =
            new LongAccumulator((left, right) -> left + right, 0);
    private static final int MAX_POOL_SIZE = 10;
    private static void add(LongAccumulator longAccumulator, long number) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(MAX_POOL_SIZE);
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            es.execute(() -> {
                for (int j = 0; j < MAX_POOL_SIZE; j++) {
                    longAccumulator.accumulate(number);
                }
            });
        }
        es.shutdown();
        es.awaitTermination(5, TimeUnit.MINUTES);
        System.out.printf("LongAccumulator %s*%s + %s 结果：%s，耗时：%sms.\n",
                MAX_POOL_SIZE,
                MAX_POOL_SIZE,
                number,
                longAccumulator.get(),
                (System.currentTimeMillis()) - start);
        longAccumulator.reset();
    }
    public static void main(String[] args) throws InterruptedException {
        // 和LongAdder一致
        add(longAccumulatorAdd, 1);
        // 每次累加2
        add(longAccumulatorAdd, 2);
    }
}
