package com.test.question.multi_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorTest2 {
    private static LongAccumulator longAccumulatorMax =
            new LongAccumulator(Long::max, 50);
    private static final int MAX_POOL_SIZE = 10;
    private static void max(LongAccumulator longAccumulator) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(MAX_POOL_SIZE);
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            int finalI = i;
            es.execute(() -> {
                for (int j = 0; j < MAX_POOL_SIZE; j++) {
                    longAccumulator.accumulate(finalI * 10);
                }
            });
        }
        es.shutdown();
        es.awaitTermination(5, TimeUnit.MINUTES);
        System.out.printf("LongAccumulator 求最大值结果：%s，耗时：%sms.\n",
                longAccumulator.get(),
                (System.currentTimeMillis()) - start);
        longAccumulator.reset();
    }
    public static void main(String[] args) throws InterruptedException {
        // 求最大值
        max(longAccumulatorMax);
    }
}
