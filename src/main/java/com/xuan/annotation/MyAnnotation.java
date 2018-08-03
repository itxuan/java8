package com.xuan.annotation;

import java.lang.annotation.*;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/3 10:42  八月
 * @Description:
 * @ModifyBy:
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE})
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {

    String[] value() default "";
}
