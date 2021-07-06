package com.leetcode.class01;

import java.util.HashMap;

/**
 * @description:
 * 给定一个数组arr，你可以在每个数字之前决定+或者-，
 * 但是所有数字必须参与
 * 再给定一个数字target，请问最后算出target的方法数是多少
 * @author: hk2018
 * 建议看一下体系学习班的 动态规划
 * @create: 2021-06-03 22:42
 */
// LeetCode 494题
public class Code07_TargetSum {
    public static int findTargetWays(int arr[],int s) {
        process1(arr,0,s);
        return  process2(arr,0,s,new HashMap<Integer, HashMap<Integer, Integer>>());
    }

    // 动态规划的方法

    // 暴力递归

    /**
     * 比如arr[ 1,3,-2,4],让你target =1 ，求方法数
     * 第一步，我们让第0个位置上的数字=1，有几种方法，首先加正号，第0个位置上的数字是+1，那么后面的数字你只要给我凑出来0就可以；
     * 其次，我们让1的前面变为负数为-1，要想得到target=1，则后面的数你要给我搞到2，
     * 把上面的两种情况相加，不断的递归，就得到了总方法数
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    private static int process1(int[] arr, int index ,int rest){
        if (rest==arr.length){ //没数了
            return rest==0? 1:0 ;
        }
        // 还有数
        return process1(arr,index+1,rest-arr[index]) +
                process1(arr,index + 1,rest +arr[index]);

    }


    /**
     * 计划搜索
     *  傻缓存法
     *  就是把上面的结果放入到一个hashmap中进行读写，就不用再算了
     */
    private static int process2(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp){
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        // 否则。未命中
        int ans = 0;
        if(index == arr.length){
            return rest==0?1:0;
        }else {
            ans = process2(arr,index,rest-arr[index],dp) +process2(arr,index,rest+arr[index],dp) ;
        }
        // 把结果塞进hashmap里面
        if (!dp.containsKey(index)) {
            dp.put(index,new HashMap<Integer, Integer>());
        }
        // 比如说 index= 3， rest =38,ans =200,意思是递归到index=3的时候，如果要凑出结果=38的数字，有200种方法数
        // 把他们放到hashmap里面，这样的话，下次在遇到就能直接取出来了
        dp.get(index).put(rest,ans);

        return ans;
    }


    /**
     * 上面的方法依然可以有优化的空间
     * 1、把数组变为全正数，并不影响所得方法数
     * 2、加起来的数字如果小于target,是0种
     * 3、加起来的数字的奇偶性和target一定是一样的
     * 4、若所有数字分为两组P和N，P-N=T，则表示有这样的一种办法使T成立
     * 那么P-N+P+N = T+P+N ,则2P=T+SUM ,主要看存在多少组能搞出P的值的方法，就是所要求的的方法数
     * 这样搞数字规模变小了
     * 纯背包问题 背包动态规划
     * 5、二维动态规划的空间压缩技巧
     */
    public static int process3(int[] arr,int target){
        int sum = 0;
        for(int n: arr){
            sum += n;
        }
        return sum<target||((target &1 )^(sum&1))!= 0 ?0:subset(arr,(target+sum)>>1);

    }

    /**
     * 二维动态规划的空间压缩技巧  暂时看不懂为什么这么写。18-23节体系学习班的课程
     * @param nums
     * @param s
     * @return
     */
    public static  int subset(int[] nums,int s){
        int[] dp= new int[s+1];
        dp[0] =1 ;
        for(int n: nums){
            for (int i=s;i>=n;i--){
                dp[i] +=dp[i-n];
            }
        }
        return dp[s];
    }

}