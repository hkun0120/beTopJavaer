package com.thread;

import java.util.concurrent.RecursiveAction;

/**
 * @description:
 * @author: H.K
 * @create: 2021-09-08 13:44
 */
public class PrintTask extends RecursiveAction {

    private static final int THRESHOLD = 9;

    private  int start;

    private  int end;

    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if(end - start  < THRESHOLD) {
            for(int i=start;i<=end;i++) {
                System.out.println(Thread.currentThread().getName()+",i="+i);
            }
        }else {
            int middle = (start + end) / 2;
            PrintTask firstTask = new PrintTask(start,middle);
            PrintTask secondTask = new PrintTask(middle+1,end);
            invokeAll(firstTask,secondTask);
        }
    }
}
