package com.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: H.K
 * @create: 2021-09-08 13:50
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) throws Exception{
        testNoResultTask();
//        testHasResultTask();
    }

    private static void testNoResultTask() throws InterruptedException{
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(1,50));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }

    public static void testHasResultTask() throws Exception {
        int result1 = 0;
        for (int i = 1; i <= 1000000; i++) {
            result1 += i;
        }
        System.out.println("循环计算 1-1000000 累加值：" + result1);

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = pool.submit(new CalculateTask(1, 1000000));
        int result2 = task.get();
        System.out.println("并行计算 1-1000000 累加值：" + result2);
        pool.awaitTermination(200, TimeUnit.MILLISECONDS);
        pool.shutdown();
    }
}
