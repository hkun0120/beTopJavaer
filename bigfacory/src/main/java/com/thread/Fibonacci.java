package com.thread;

import java.util.concurrent.RecursiveTask;

/**
 * @description:
 * @author: H.K
 * @create: 2021-09-08 10:53
 */
public class Fibonacci extends RecursiveTask<Integer> {
    final int n;
    Fibonacci(int n ){
        this.n = n;
    }
    @Override
    protected Integer compute() {
        if(n<=1){
            return n;
        }
        Fibonacci f1 = new Fibonacci(n-1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n-2);
        Integer f2com = f2.compute();
        Integer f1join = f1.join();

        System.out.println("f1join   "+ f1join + " n = "+n);
        System.out.println("f2com    "+ f2com  + " n = "+n);
        System.out.println("f1compute    "+ f1.compute()  + " n = "+n);
        return f2com+f1join;
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci(5).compute() + " done");
    }
}
