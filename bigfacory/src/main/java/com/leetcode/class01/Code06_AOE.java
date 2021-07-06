package com.leetcode.class01;

/**
 * @description:
 * 给定两个非负属组x和HP，长度都为N，再给定一个正数range
 *  *  x有序，x[i]表示i号怪兽在X轴上的位置；hp[i]代表怪兽i的血量
 *  *  range表示若法师站在x位置，用AOE技能达到的范围是[X-RANGE,X+RANGE]
 *  *  被打到的每只怪损失1点血
 *  *  返回要把所有怪兽血量清空，需要释放多少次AOE技能
 *  *
 *  *  用线段树
 *  *  O(N*logN)
 * @author: hk2018
 * @create: 2021-06-03 22:41
 */
public class Code06_AOE {
    // 纯暴力解法
    public static int minAOE1(int[]x ,int[] hp,int range){
        int N = x.length;
        return 0;
    }
}