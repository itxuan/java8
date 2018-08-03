package com.xuan.annotation;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/3 10:47  八月
 * @Description:
 * @ModifyBy:
 */

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyAnnotations {

    MyAnnotation[] value();
}
