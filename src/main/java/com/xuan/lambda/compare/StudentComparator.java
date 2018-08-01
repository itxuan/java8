package com.xuan.lambda.compare;

import com.xuan.lambda.model.Student;

import java.util.Comparator;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/30 19:36  七月
 * @Description:
 * @ModifyBy:
 */
public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {

        return o2.getId()-o1.getId();
    }
}
