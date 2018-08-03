package com.xuan;

import com.xuan.annotation.MyAnnotation;
import com.xuan.annotation.MyAnnotations;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/3 10:44  八月
 * @Description:
 * @ModifyBy:
 */

//@MyAnnotation(value = "浙江")
//@MyAnnotation(value = "北京")
@MyAnnotations(value = {
        @MyAnnotation(value = "北京")
        ,@MyAnnotation(value = "浙江")
})
public class Address {

    @MyAnnotation(value = "上海")
    public void show(){

    }

}
