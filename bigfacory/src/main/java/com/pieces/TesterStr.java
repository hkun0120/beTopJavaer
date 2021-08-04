package com.pieces;

import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @author: H.K
 * @create: 2021-07-23 14:56
 */
public class TesterStr {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str1 = "hello";

        String str2 = "你好abc";
        String str3 = "";
        System.out.println(str3.trim());
        System.out.println("utf-8编码下'hello'所占的字节数:" + str1.getBytes("utf-8").length);

        System.out.println("gbk编码下'hello'所占的字节数:" + str1.getBytes("gbk").length);

        System.out.println("utf-8编码下'你好abc'所占的字节数:" + str2.getBytes("utf-8").length);

        System.out.println("gbk编码下你好'你好abc'所占的字节数:" + str2.getBytes("gbk").length);

        People people =new People();
        people.setAge(12);
        people.setName("dd");
        
    }

    static class People{
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
