package com.clic.thinkinginspringboot.service;

import com.clic.thinkinginspringboot.bean.RouteInfo;
import com.clic.thinkinginspringboot.config.ExecutorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: H.K
 * @create: 2020-09-09 16:38
 */
@Service
public class ExecutorService {
    @Autowired
    ExecutorConfig executorConfig;

    /**
     * 测试ThreadPoolTaskExecutor在执行多线程的时候是否能
     */
    public void test() throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) executorConfig.customServiceExecutor();
        List<Future<List<String>>> listTasks = new ArrayList();
        String cntrNo= "1";
        String branchNo ="branchno";
        String sysNo = "sysno";
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setBranchNo(branchNo);
        routeInfo.setSysNo(sysNo);
        QueryTask queryTask = new QueryTask(cntrNo,routeInfo);
        Future<List> future = threadPoolTaskExecutor.submit(queryTask);
        for(int i=1;i<5;i++){
            int time = i*1000;
            QueryTask queryTask1 = new QueryTask(cntrNo,routeInfo,time);

            Future<List<String>> future1 = threadPoolTaskExecutor.submit(queryTask1);
            /*
            核心在这里，先把所有的任务装起来，集体发送给线程池，由线程池进行并发查询
             */
            listTasks.add(future1);

        }
        long start = System.currentTimeMillis();
        for(Future future1: listTasks) {
            List<String> list = (List<String>) future1.get();
            for(String string:list){
                System.out.println(string+"    "+ " 111111get done");
            }
        }
        System.out.println("spend time :"+(System.currentTimeMillis()-start)+"ms");
        List<String> list = future.get();
        for(String string:list){
            System.out.println(string+"    "+ " get done");
        }
    }

    /**
     * 加入线程池，然后跟着就futuretask.get()
     * 再加入线程池，再get()
     * 测试线程池的执行顺序
     */
    public void orderThread() throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) executorConfig.customServiceExecutor();
        List<Future<List<String>>> listTasks = new ArrayList();
        String cntrNo= "1";
        String branchNo ="branchno";
        String sysNo = "sysno";
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setBranchNo(branchNo);
        routeInfo.setSysNo(sysNo);
        QueryTask queryTask = new QueryTask(cntrNo,routeInfo);
        Future<List> future = threadPoolTaskExecutor.submit(queryTask);
        for(int i=1;i<5;i++){
            int time = i*1000;
            QueryTask queryTask1 = new QueryTask(cntrNo,routeInfo,time);

            Future<List<String>> future1 = threadPoolTaskExecutor.submit(queryTask1);
            System.out.printf("直接执行future.get()方法，看看线程池是否要等到其他的线程一起");
            List<String> list = future1.get();
            for(String string:list){
                System.out.println(string+"    "+ " 111111get done");
            }
            System.out.println("直接执行结束了，不会等待其他线程，get()方法就是触发了执行...........");
            /*
            核心在这里，先把所有的任务装起来，集体发送给线程池，由线程池进行并发查询
             */
            listTasks.add(future1);

        }
//        long start = System.currentTimeMillis();
//        for(Future future1: listTasks) {
//            List<String> list = (List<String>) future1.get();
//            for(String string:list){
//                System.out.println(string+"    "+ " 111111get done");
//            }
//        }
//        System.out.println("spend time :"+(System.currentTimeMillis()-start)+"ms");
//        List<String> list = future.get();
//        for(String string:list){
//            System.out.println(string+"    "+ " get done");
//        }
    }
    private class QueryTask<T> implements Callable<List<T>>{
        private String cntrNo;
        private RouteInfo routeInfo;
        private int time;
        public QueryTask(String cntrNo,RouteInfo routeInfo){
            this.cntrNo = cntrNo;
            this.routeInfo = routeInfo;
        }
        public QueryTask(String cntrNo,RouteInfo routeInfo,int time){
            this.cntrNo = cntrNo;
            this.routeInfo = routeInfo;
            this.time = time;
        }
        @Override
        public List<T> call() throws Exception {
            List list = new ArrayList();
            list.add(cntrNo);
            list.add(routeInfo.getBranchNo());
            list.add(routeInfo.getSysNo());
            System.out.println("系统进入沉睡，沉睡时间"+time+"ms");
            Thread.sleep(time);
            return list;
        }
    }
}
