package com.lock.keywords;

/**
 * @description: 看看编译出来class文件
 * @author: H.K
 * @create: 2021-07-14 14:53
 */
public class SyncByteCode {
    public static SyncByteCode instance;
    public static void main(String[] args) {

        if (null == instance) {
            synchronized (SyncByteCode.class) {
                if (null == instance) {
                    instance = new SyncByteCode();
                }else{

                }
            }
        }else {

        }
    }
}
