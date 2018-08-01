package com.xuan.lambda;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/31 15:03  七月
 * @Description:
 * @ModifyBy:
 */
public interface MyFunction<T,R> {

    R accept(T t1,T t2);
}
