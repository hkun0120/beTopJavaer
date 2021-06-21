package com.msb.learning.thinkinginspringboot.basic;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: JAVA 基础
 * @author: H.K
 * @create: 2020-08-07 13:41
 */
public class JavaFoundation {
    public static void main(String[] args) throws ParseException {
        System.out.println("请输入测试用例数量：");
        Scanner scanner=new Scanner(System.in);
        String string = null;
        int num =0 ;
        string = scanner.next();
        System.out.println("您输入的测试用例数量为："+string);

        for(int j=0;j<Integer.parseInt(string);j++) {
            System.out.println("请输入病毒码字符串数量：");
            Scanner scanner1 = new Scanner(System.in);
            String virusLength = null;
            virusLength = scanner1.next();
            System.out.println("您输入的字符为：" + virusLength);

            System.out.println("请病毒码和源字符码，病毒码为" + virusLength + "行,并在最后一段输入源字符串");
            Vector<String> datas = new Vector<>();
            for (int i = 0; i <= Integer.parseInt(virusLength); i++) {
                Scanner scanner2 = new Scanner(System.in);
                String data = scanner2.next();
                datas.add(data);
            }
            for (int k=0;k<datas.size()-1;k++) {
                if (datas.get(datas.size()-1).contains(datas.get(k)) || datas.get(datas.size()-1).contains(fan(datas.get(k)))) {
                    num++;
                }
            }
            System.out.println(num);
//            System
        }
//        json();
//        date();
//        A();
//        div();;
    }

    public static String fan(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }

    public static void json(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a","b");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        System.out.println(jsonArray.toJSONString());
    }

    public static void div(){
        int a = 7;
        String b = "17";
        Float f =99f;
        System.out.println(Float.parseFloat(b)/a);
        System.out.println(f/a);
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
        Boolean aa = null;
        if(StringUtils.isEmpty(aa)){
            System.out.println("ddddddddd");
        }
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

        String [] a="-".split("-");
        System.out.println("ddd");
        System.out.println(a.length>0);

    }
}
