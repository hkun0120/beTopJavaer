package com.msb.learning;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * @description:
 * @author: H.K
 * @create: 2021-02-22 15:01
 */
public class base {
    public static void main(String[] args) {
        JSONObject jsonObject =null;
        String aa="a";
        String aa1="的的的的的";
        ArrayList list = new ArrayList();
        list.add("aa");
        list.set(0,"dddd");
        System.out.println(list.get(0));
        aa.substring(0,0);
        System.out.println(aa.length());
        System.out.println(aa1.length());
        System.out.println(aa.getBytes().length);
        System.out.println(aa1.getBytes() .length);
        System.out.println(0%999==0);

    }


}
