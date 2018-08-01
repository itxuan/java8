package com.xuan.lambda.model;

import com.xuan.lambda.enums.GenderEnum;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/30 19:27  七月
 * @Description:
 * @ModifyBy:
 */
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private GenderEnum gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Student(){}

    public Student(Integer id){
        this.id = id;
    }
    public Student(Integer id, String name, Integer age, GenderEnum gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static GenderEnum defaultGender(){
        return GenderEnum.MAN;
    }
    @Override
    public String toString() {
        return "Student{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
