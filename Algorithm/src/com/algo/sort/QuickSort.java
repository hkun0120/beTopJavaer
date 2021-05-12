package com.algo.sort;

/**
 * @description:快速排序算法
 * @author: H.K
 * @create: 2021-05-10 17:17
 */
public class QuickSort {
    public static void main(String[] args) {
        int [] arr = {1,3,5,2,3,8,9,4};
//        int [] res = partition(arr,0,arr.length-1);
//        System.out.println(res[0]);
//        System.out.println(res[1]);
//        for (int a:arr
//             ) {
//            System.out.println(a);
//        }
//        quickSort2(arr);
        quickSort3(arr,0,arr.length-1);
        for (int a:arr
        ) {
            System.out.println("++++ "+a);
        }
        quickSort(arr);
        for (int a:arr
        ) {
            System.out.println("==== "+a);
        }
    }

    /**
     * 快排2.0，时间复杂度为O(n²)
     * @param arr
     */
    public static void quickSort2(int[] arr){
       int[] ab = partition(arr,0,arr.length-1);
       partition(arr,0,ab[0]-1);
       partition(arr,ab[1]+1,arr.length-1);
       for (int a:arr
        ) {
            System.out.println(a);
        }
    }

    /**
     * 快排3.0 ，时间复杂度为O(N*LogN)
     */
    public static void quickSort3(int[] arr,int l, int r){
        if (l < r) {
            swap(l + (int) (Math.random() * (r - l + 1)), r, arr);
            int[] ab = partition1(arr, l, r);
            quickSort3(arr, l, ab[0]-1);
            quickSort3(arr, ab[1] + 1, r);
        }
    }

    public static void process(int L,int R,int C,int[] arr){
        if(arr.length<2){
            return;
        }
        int temp = arr[C];
        if(temp < arr[L]){
            swap(L,C,arr);
        }
    }

    // 经典快速排序，根据属组最后一个元素X，把属组分为小于X和大于X的两个区域
    // 也就是荷兰国旗问题
    // 返回分组后确定的=num的范围
    public static int[] partition1(int[] arr, int left ,int right){
        int less = left - 1; // <区的开始位置
//        int more =right +1 ; // 大于区的开始位置
        int more =right  ; // 大于区的开始位置
        int index = left;
        int target = arr[right];
        while(index < more){ // index 表示当前值
            if (arr[index] == target){
                index ++;
            }
            else if( arr[index] < target){
                swap(index ++ ,++less,arr); // 小于区扩张，当前值增加
            }
            else if(arr[index]>target){
                swap(index,--more,arr); // 大于区缩小，交换，当前值不变
            }
        }
        // 这部是因为选取最右侧的值作为比较对象，同时开始的more位置是right而不是right+1，
        // 所以最后这个数字的位置始终没有动过，把它与大于区的最后一个位置交换，这个时候就排好了
        // 左神牛逼
        // 如果more的初始位置为right+1，那么就是经典的荷兰国旗问题，也可以的，下面的patition方法就是经典荷兰国旗问题的实现
        swap(more,right,arr);
        return new int[]{less+1,more};
    }
    public static void swap(int a, int b,int[] arr){
        int temp;
        temp =arr[a];
        arr[a] = arr[b];
        arr[b] =temp;

    }

    public static void swap(int[] arr,int a, int b){
        int temp;
        temp =arr[a];
        arr[a] = arr[b];
        arr[b] =temp;

    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }


    public static void quickSort(int[] arr, int left, int right) {
        //多余判断
        //if (left == right) {
        //    return;
        //}
        if (left < right) {
            //随机快排核心
            //Math.random() 取值范围 [0, 1)
            //(Math.random() * (right - left + 1)) 此处数值 <= (right - left)，因此可以保证参数 first 在区间内
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0] - 1);
            quickSort(arr, p[1] + 1, right);
        }
    }


    public static int[] partition(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right + 1;
        //int more = right;
        //left 成为数组遍历的 cur 指针，当触碰到 more 边界时终止循环
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, less + 1, left);
                less++;
                left++;
                //左神代码
                //swap(arr, ++less, left++);
            } else if (arr[left] == arr[right]) {
                left++;
            } else {
                swap(arr, left, more - 1);
                more--;
                //左神代码
                //swap(arr, left, --more);
            }
        }
        return new int[]{less + 1, more };
        //return new int[]{less + 1, more};
    }
}
