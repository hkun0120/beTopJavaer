package com.thread;

/**
 * @description:
 * @author: H.K
 * @create: 2021-09-08 14:18
 */
import java.util.concurrent.RecursiveTask;

public class CalculateTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    // 每个线程拿到的子任务分解的阈值是49
    private static final int THRESHOLD = 49;
    private int start;
    private int end;

    public CalculateTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int result = 0;
            for (int i = start; i <= end; i++) {
                result += i;
            }
            return result;
        } else {
            int middle = (start + end) / 2;
            CalculateTask firstTask = new CalculateTask(start, middle);
            CalculateTask secondTask = new CalculateTask(middle + 1, end);
            invokeAll(firstTask,secondTask);
            return firstTask.join() + secondTask.join();
        }
    }

}