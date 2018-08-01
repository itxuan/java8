package com.xuan.lambda;

import java.util.Objects;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/31 14:29  七月
 * @Description:
 *          @FunctionalInterface
 *          标记下的函数式接口，有且仅有一个未实现的方法
 *          可以有default 修饰的默认方法
 * @ModifyBy:
 */
@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);

    default MyPredicate<T> and(MyPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
}
