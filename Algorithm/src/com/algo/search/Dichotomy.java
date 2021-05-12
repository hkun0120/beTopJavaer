package com.algo.search;

/**
 * @description: 二分法查找算法
 * @author: H.K
 * @create: 2021-04-20 17:10
 */
public class Dichotomy {
    public static void main(String[] args) {
        swap(1,2);
//        getLessIndex(new int[]{299, 219, -1, 8, 6, -2, 9, 54});
    }

    // 查找局部最小值
    public static int getLessIndex(int[] arr){
        if(arr==null||arr.length==0) {
            return -1;
        }
        if(arr.length==1 ||arr[0]<arr[1]){
            return 0;
        }
        if(arr[arr.length-1] < arr[arr.length - 2]){
            return arr.length-2;
        }
        int left = 1;
        int right = arr.length -1 ;
        int mid = 0;
        while (left < right){
            mid = (right + left)/2;
            if (arr[mid - 1] < arr[mid]){
                right = mid - 1;
            }else if ( arr[mid +1 ]<arr[mid]){
                left = mid + 1;
            }else {
                System.out.println("mid : " +mid);
                return mid;


            }
        }
        System.out.println("left : " +left);
        return left;
    }

    // 异或交换数字
    public static void swap(int a,int b){
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(a + "   " + b);
    }
}
