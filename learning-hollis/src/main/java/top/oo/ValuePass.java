package top.oo;

import top.pub.bean.StdContract;

/**
 * @description: 理解java中为什么只有值传递
 * @author: H.K
 * @program: beTopJavaer
 * @create: 2020-02-04 14:04
 */
public class ValuePass {
    public static void main(String[] args) {
        ValuePass valuePass = new ValuePass();
        int i = 10;
        valuePass.pass(i);
        System.out.println("main method i : "+i);
        StdContract stdContract  = new StdContract();
        stdContract.setApplNo("2");
        stdContract.setCntrId("2");
        stdContract.setCntrNo("2");
        valuePass.pass(stdContract);
        System.out.println("main method object:"+stdContract);
    }
    public void pass(int j){
        j = 20;
        System.out.println("pass method j : "+j);
    }

    public void pass(StdContract stdContract){
        stdContract.setApplNo("1");
        stdContract.setCntrId("1");
        stdContract.setCntrNo("1");
        System.out.println(stdContract);
    }
}
