package com.thread;

import sun.awt.windows.ThemeReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @description: 两个线程的交替输出问题（wait,notify），另外还可以描述为主线程输出一些再转移到子线程
 * 再分析一下CompletableFuture的异步输出问题，用自定义线程池和默认线程池
 * @author: H.K
 * @create: 2021-08-30 10:40
 */
public class SwitchThread {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 0L,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024));
    public static void main(String[] args)  {
        System.out.println("pppp :" + ForkJoinPool.getCommonPoolParallelism());
        System.out.println("main ===="+ new SwitchThread().completableFutureSupplyAsynDefaultPool());
        System.out.println("getActiveCount " +new SwitchThread().threadPoolExecutor.getActiveCount());

//        new SwitchThread().test();
        new SwitchThread().completableFutureSupplyAsynDefaultPool();

    }
    public void test(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("executorService 是否为守护线程 :" + Thread.currentThread().isDaemon());
                return null;
            }
        });
        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is lambda supplyAsync");
            System.out.println("supplyAsync 是否为守护线程 " + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this lambda is executed by forkJoinPool");
            return "result1";
        });
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("this is task with executor");
            System.out.println("supplyAsync 使用executorService 时是否为守护线程 : " + Thread.currentThread().isDaemon());
            return "result2";
        }, executorService);
        try {
            System.out.println(completableFuture.get());
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
    /**
     * 测试completableFuture#SupplyAsyn方法
     * @return
     */
    private  String completableFutureSupplyAsyn(){
        String res = "return message";
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                costTime();
                return "CompletableFuture supplyAsync complete get()";
            }
        },threadPoolExecutor);
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
        System.out.println(threadPoolExecutor.getActiveCount());
        future1.thenAccept(e-> System.out.println("xxxxx"+e));
//        try {
//            future1.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        Supplier<StringBuffer> supplier  = new Supplier<StringBuffer>() {
//            @Override
//            public StringBuffer get() {
//                return new StringBuffer("null??");
//            }
//        };
//        System.out.println(supplier.get());
        return res;
    }

    /**
     * 用默认的线程池，看看是否还会造成completablefuture执行完了之后，程序不退出
     * @return
     */
    private  String completableFutureSupplyAsynDefaultPool(){
        String res = "return message";
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                costTime();
                return "CompletableFuture supplyAsync complete get()";
            }
        });
        System.out.println(threadPoolExecutor.getCompletedTaskCount());
        System.out.println(threadPoolExecutor.getActiveCount());
        future1.thenAccept(e-> System.out.println("xxxxx"+e));
        future1.complete("???????");
//        try {
//            future1.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        Supplier<StringBuffer> supplier  = new Supplier<StringBuffer>() {
//            @Override
//            public StringBuffer get() {
//                return new StringBuffer("null??");
//            }
//        };
//        System.out.println(supplier.get());
        return res;
    }

    private void costTime()  {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aiyou");
    }
    /**
     * 比较join sleep方法，在主线程和子线程
     */
    public static void joinSleep(){
        Thread mainThread = Thread.currentThread();
        Thread threadA = new Thread(() -> {
            try {
                /**
                 * 一定是当前线程调用此方法，当前线程进入TIMED_WAITING状态，
                 * 但不释放对象锁，millis后线程自动苏醒进入就绪状态。
                 * 作用：给其它线程执行机会的最佳方式
                 */
                System.out.println("wtf"+Thread.currentThread().getName());
                System.out.println("wtf"+mainThread.getName());
                Thread.sleep(10000);
                System.out.println("mainThread.getState()"+mainThread.getState());
//                System.out.println("threadA.getState()"+.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                /**
                 * thread.join()/thread.join(long millis)，当前线程里调用其它线程t的join方法，
                 * t进入WAITING/TIMED_WAITING状态，当前线程不会释放已经持有的对象锁。
                 * 线程t执行完毕或者millis时间到，当前线程一般情况下进入RUNNABLE状态，
                 * 也有可能进入BLOCKED状态（因为join是基于wait实现的）
                 */
                System.out.println("ok "+Thread.currentThread().getName());
                threadA.join(1000);
                System.out.println("B mainThread.getState()"+mainThread.getState());
//                System.out.println("threadA.getState()"+.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        System.out.println("threadA"+threadA.getState());
        threadB.start();
        System.out.println("threadA"+threadA.getState());
        System.out.println("threadB"+threadB.getState());
//        threadA.join(1000);
//        Thread.sleep(1000);
        System.out.println("threadA 1 "+threadA.getState());
        System.out.println("threadB 1 "+threadB.getState());
        System.out.println("threadM 1 "+mainThread.getState());
//        Thread.sleep(10000);
//        System.out.println("threadA 2"+threadA.getState());
//        System.out.println("threadB 2"+threadB.getState());
//        System.out.println("threadM 2"+mainThread.getState());
    }
}
