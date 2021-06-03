package com.leetcode.class01;

import java.io.File;
import java.util.Stack;

/**
 * @description:
 *  题目：
 *      给定一个文件目录的路径
 *      写一个函数统计这个目录下所有的文件数量并返回
 *      隐藏文件也算，但是文件夹不算
 * @author: hk2018
 * @create: 2021-06-03 22:39
 */
public class Code02_CountFiles {
    /**
     * 宽度有限或者深度优先都可以
     * 这么简单就不要用递归做了
     * Queue队列做，文件直接count++，文件夹就放入队列
     */
    public static int getFileNumber(String filePath){
        File root = new File(filePath);
        if(root.isFile()){
            return 1;
        }
        if(!root.isDirectory()&&!root.isFile()){
            return 0;
        }
        Stack<File> stack = new Stack<>();
        stack.push(root);
        int files = 0;
        while (!stack.isEmpty()){
            File file = stack.pop();
            for(File f : file.listFiles()){
                if(f.isFile()) {
                    files++;
                }
                if(f.isDirectory()){
                    stack.push(f);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        int num =getFileNumber("C:\\Users\\Administrator\\Documents\\Tencent Files\\393743693\\FileRecv");
        System.out.println(num);
    }
}