package com.msb.learning.thinkinginspringboot.basic;

import com.msb.learning.thinkinginspringboot.util.Singleton;

import java.text.ParseException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @description:
 * @author: H.K
 * @create: 2020-12-21 14:49
 */
public class Test {
    public static void main(String[] args){
        System.out.println(Singleton.INSTANCE);
        People people =new People();
        people.setHeight("dddd");
        System.out.println(people.toString());
    }
    public static String fan(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }
}
