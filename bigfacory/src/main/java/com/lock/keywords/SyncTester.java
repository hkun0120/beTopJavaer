package com.lock.keywords;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description: 通过编译底层class文件分析synchronized与CAS的原理以及使用方法
 * OracleJDK 1.6之后对sync进行了优化
 * @author: H.K
 * @create: 2021-07-14 14:46
 */
public class SyncTester {
    /**
     * 对静态方法的修饰可以和实例方法的修饰同时使用，不会阻塞，因为一个是修饰的Class类，一个是修饰的实例对象
     */
    public static synchronized void StaticSyncTest() {

        for (int i = 0; i < 3; i++) {
            System.out.println("StaticSyncTest");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public synchronized void NonStaticSyncTest() {

        for (int i = 0; i < 3; i++) {
            System.out.println("NonStaticSyncTest");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final SyncTester synchronizedTest = new SyncTester();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.StaticSyncTest();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.NonStaticSyncTest();
            }
        }).start();
//        HashMap
    }
}
