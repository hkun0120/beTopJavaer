package com.msb.learning.algorithm;

/**
 * @description: 位运算
 * @author: H.K
 * @create: 2021-03-02 17:02
 */
public class Bytes {

    public static void print(int num){
        for(int i=31;i>=0;i--){
            System.out.print((num&(1<<i))==0?"0":"1");
        }
        System.out.println();
    };

    public static void main(String[] args) {
//        for(int i=31;i>=0;i--){
//            System.out.print((1<<i));
//        }
//        System.out.print((1<<4));
        print(5);
        print(-5);
        print(2222|1111);
        print(2222^1111);
        print(2222&1111);
        int  a =Integer.MIN_VALUE;
        int b = ~a +1;
        print(a);
        print(b);
        System.out.println(a);
    }
}
