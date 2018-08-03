package com.xuan.lambda;

import com.xuan.Address;
import com.xuan.annotation.MyAnnotation;
import com.xuan.annotation.MyAnnotations;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/3 10:43  八月
 * @Description:
 * @ModifyBy:
 */
public class AnnotationTest {

    @Test
    public void testAnnotation(){

        MyAnnotations[] annotationsByType = Address.class.getAnnotationsByType(MyAnnotations.class);
        for (MyAnnotations mas:annotationsByType) {

            System.out.println(mas.value());
            String s = Arrays.toString(mas.value());
            
            System.out.println(s);
        }
    }
}

