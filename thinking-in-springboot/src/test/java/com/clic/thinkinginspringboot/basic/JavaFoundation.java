package com.clic.thinkinginspringboot.basic;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: JAVA 基础
 * @author: H.K
 * @create: 2020-08-07 13:41
 */
public class JavaFoundation {
    public static void main(String[] args) throws ParseException {
        json();
        date();
        A();
    }

    public static void json(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a","b");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        System.out.println(jsonArray.toJSONString());
    }

    private static void date() throws ParseException {
        String d = "1990-01-02";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println((Date)simpleDateFormat.parse(d));
        Calendar calendar = Calendar.getInstance();
        Date date = simpleDateFormat.parse(d);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        Date date1 = calendar.getTime();

        System.out.println(simpleDateFormat.format(date1));
    }

    private static void A(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String str="20110823";
        Date dt= null;
        try {
            dt = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR,-1);//日期减1年
        rightNow.add(Calendar.MONTH,3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);

    }
}
