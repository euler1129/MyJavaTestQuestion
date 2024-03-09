package com.test.question.multi_thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskTest extends RecursiveTask<Long> {
    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 1000L;
    private long start;
    private long end;
    public ForkJoinTaskTest(long start, long end) {
        this.start = start;
        this.end = end;
    }
    private static void test(){
        System.out.println("test - serial computing");
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0L; i <= MAX; i++){
            sum += i;
        }
        System.out.println("result: " + sum);
        System.out.println("take time: " +
                (System.currentTimeMillis() - start) + "ms");
    }
    private static void testForkJoin(){
        System.out.println("testForJoin - parallel computing");
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new ForkJoinTaskTest(1, MAX));
        System.out.println("result: " + sum);
        System.out.println("take time: " +
                (System.currentTimeMillis() - start) + "ms");
    }
    @Override
    protected Long compute() {
        long sum = 0;
        if(end - start <= THRESHOLD){
            for (long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        } else {
            long mid = (start + end) / 2;
            ForkJoinTaskTest task1 = new ForkJoinTaskTest(start, mid);
            task1.fork();
            ForkJoinTaskTest task2 = new ForkJoinTaskTest(mid + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
    public static void main(String[] args) {
        test();
        System.out.println("--------------------------------");
        testForkJoin();
    }
}
