package com.leetcode.class01;

import java.util.HashSet;

/**
 * @description: 删除链表中的重复节点
 * @author: H.K
 * @create: 2021-06-22 15:16
 */
public class Code08_RemoveRepeatitionNode {
    private static class Node {
        Object value;
        Node next;
        public Node(Object value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 6, 4, 2, 7, 6, 8, 1};
        Node head = new Node(null);//哑元
        Node p = head;
        for (int i = 0; i < arr.length; i++) {
            p.next = new Node(arr[i]);
            p = p.next;
        }
        rr(head);//有缓冲区

// rr2(head);//无缓冲区

        Node p1 = head.next;
        while (p1 != null) {
            System.out.println(p1.value);
            p1 = p1.next;
        }

    }

    /**
     * 使用缓冲区
     *
     * @param head
     */
    private static void rr(Node head) {
        HashSet set = new HashSet();//开辟新空间
        Node p1 = head.next;
        Node pre = head;
        while (p1 != null) {
            if (set.contains(p1.value)) {//重复
                System.out.println("delete:" + p1.value);
                pre.next = p1.next;//删除
            } else {
                set.add(p1.value);//加入队列
                pre = p1;//更新前驱pre 很重要！！！ 注意理解
            }
            p1 = p1.next;
        }
    }


}
