package com.xuan;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/2 11:54  八月
 * @Description:
 * @ModifyBy:
 */
public interface MyInterface1 {

    default String getName(){
        return "MyInterface1";
    }

    static void say(){
        System.out.println("say hello");
    }
}
