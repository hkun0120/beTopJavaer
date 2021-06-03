package com.leetcode.class01;

/**
 * @description:
 *  题目：
 *      给定一个有序数组arr,代表落在X轴上的点
 *      给定一个正数K，代表绳子的长度
 *      返回绳子最多压中几个点？
 *      即使绳子边缘处盖住点也算盖住
 * @author: hk2018
 * @create: 2021-06-03 21:43
 */
public class Code01_CordCoverMaxPoint {
    /** 贪心算法开头
    // 二分查找
    // 上面写的都用不到
    // 直接从左边向右找，算法复杂度是O(N)
     */
    public static int maxPoint2(int[] arr, int L){
        int left = 0;
        int right = 0;
        int N= arr.length;
        int max = 0;
        while (left < N){
            while (right < N && arr[right] -arr[left] <=L ){
                right ++;
            }
            max = Math.max(max,right -left);
            left ++;

        }
        return max;
    }

    // 对数器，用于测试
    public static int test(int[] arr,int L){
        int max = 0;
        for (int i = 0; i < arr.length ; i++){
            int pre = i - 1;
            while (pre > 0 && arr[i]-arr[pre] <=L){
                pre --;
            }
            max = Math.max(max,i - pre);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,6,7,9,10,13};
        int L=4;
        System.out.println(test(arr,L));
        System.out.println(maxPoint2(arr,L));
    }
}