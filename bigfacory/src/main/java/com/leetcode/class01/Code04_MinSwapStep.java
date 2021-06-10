package com.leetcode.class01;

/**
 * @description:
 *  题目：
 *      一个数组中只有两种字符G和B，
 *      想让所有的G都放入左侧，所有的B都放入右侧
 *      但是只能在相邻的字符之间进行交换操作
 *      返回至少需要交换几次
 *
 * @author: hk2018
 * @create: 2021-06-03 22:40
 */
public class Code04_MinSwapStep {
    /**
     * 时间复杂度为O(N)最优解
     * 两个变量即完成排序
     * 下面的算法是解了这样的题目（G 、B可以左边或者右边，所以代码里需要求G、B两种情况下的最小移动次数）
     */
    public static int minStep1(String s){
        if(s ==null||s.equals("")){
            return 0;
        }
        char[] arr =s.toCharArray();
        int gi = 0;
        int step1 =0;
        for(int i = 0; i<arr.length;i++){
            if(arr[i] =='G'){
                step1 += i - (gi++);
            }
        }

        int step2 = 0;
        int bi =0;
        for(int i= 0 ;i<arr.length;i++){
            if(arr[i] =='B'){
                step2 += i - (bi++);
            }
        }
        return Math.min(step1,step2);

    }

    public static void main(String[] args) {
        System.out.println(minStep1("BBBGGB"));
    }
}