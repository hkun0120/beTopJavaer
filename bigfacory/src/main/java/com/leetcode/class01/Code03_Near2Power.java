package com.leetcode.class01;

/**
 * @description:
 * 题目：
 *      给定一个非负整数num，
 *      如何不用循环语句，
 *      返回>=num，并且离num最近的2的某次方
 * @author: hk2018
 * @create: 2021-06-03 22:39
 */
public class Code03_Near2Power {
    public static void main(String[] args) {
        int i = 2>>3;
        System.out.println(i);
        System.out.println(tableSizeFor(120));
    }

    /**
     * 填满函数， | 代表或， >>>无符号位右移 ，n和右移之后的数字进行或运算，就逐渐把右边的数字变为了1，
     * 比如，n=29，011101 右移1位变为001110，或运算变为011111 再移动两位，变为000111，再或变为011111，依此类推
     * 最后得到011111
     * 为什么先-1？因为当数字正好是2的n次方时，先减最后+，还是这个数本身
     * 为什么右移1，2，4，8，16 加起来是31，int类型一共就32位
     * 为什么最后要判断n<0，如果是个负数的话，2的n次方最低也是1，离负数最近的就是2的0次方1
     * >> 右移，若负数，则用1来补首位
     * @param n
     * @return
     */
    public static int tableSizeFor(int n){
        n --;
        n |= n>>>1;
        n |= n>>>2;
        n |= n>>>4;
        n |= n>>>8;
        n |= n>>>16;

        return (n<0)?1:n +1;

    }
}