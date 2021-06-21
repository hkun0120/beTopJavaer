package com.msb.learning.thinkinginspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.DataInputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: H.K
 * @create: 2020-09-09 16:35
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

//    @Bean
//    public Executor asyncServiceExecutor() {
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = new VisiableThreadPoolTaskExecutor();
//        //核心线程数
//        threadPoolTaskExecutor.setCorePoolSize(5);
//        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
//        //最大线程数
//        threadPoolTaskExecutor.setMaxPoolSize(5);
//        //配置队列大小
//        threadPoolTaskExecutor.setQueueCapacity(50);
//        //配置线程池前缀
//        threadPoolTaskExecutor.setThreadNamePrefix("async-service-");
//        //拒绝策略
////        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new PrintingPolicy());
//        threadPoolTaskExecutor.initialize();
//        return threadPoolTaskExecutor;
//    }

    @Bean
    public Executor customServiceExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        //线程核心数目
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //配置队列大小
        threadPoolTaskExecutor.setQueueCapacity(50);
        //配置线程池前缀re
        threadPoolTaskExecutor.setThreadNamePrefix("custom-service-");
        //配置拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //数据初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
