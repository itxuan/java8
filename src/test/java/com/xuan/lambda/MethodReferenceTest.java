package com.xuan.lambda;

import com.xuan.lambda.enums.GenderEnum;
import com.xuan.lambda.model.Student;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/31 19:17  七月
 * @Description:
 *       方法引用: 若Lambda 体中的内容有方法已经实现了
 *       有三种方法引用：
 *          对象::实例方法名
 *          类::静态方法名
 *          类::实例方法名
 *
 *       构造器引用
 *          ClassName :: new
 * @ModifyBy:
 */
public class MethodReferenceTest {


//    对象::实例方法名
    @Test
    public void test1(){

        Consumer<String> consumer = x ->System.out.println(x);
        consumer.accept("hello world");
        System.out.println("----------分割线---------------");
        PrintStream printStream = System.out;
        Consumer<String> consumer1 = x -> { printStream.println(x); };
        consumer1.accept("hello water");
        System.out.println("----------分割线---------------");
        Consumer<String> con = printStream::println;
        con.accept("Print");
    }

//    类::静态方法名
    @Test
    public void test2(){
        Student student = new Student(1,"宣",23,GenderEnum.MAN);
        Supplier<String> supplier = student::getName;
        Supplier<String> sup = () -> {return student.getName();};
        System.out.println(sup.get()  +"," + supplier.get());


        System.out.println("===============================================");
        Supplier<GenderEnum> after = Student::defaultGender;
        System.out.println(after.get());
        Comparator<Integer> comparator = Integer::compare;
    }

//    类::实例方法名

    @Test
    public void test3(){
        //  条件 参数x 是实例方法的调用者，参数y 是实例方法的参数
        BiPredicate<String,String> biPredicate =(x ,y) -> {return  x.equals(y);};
        System.out.println(biPredicate.test("stu","stu"));

        BiPredicate<String,String> biPredicate1 = String::equals;

    }

    // 构造器引用
    @Test
    public void test4(){

        Supplier<Student> studentSupplier = Student::new;
        System.out.println(studentSupplier.get());
        Function<Integer,Student> function = Student::new;
        System.out.println(function.apply(10086));
        System.out.println("============================================");
        Function<Integer,ArrayList<Student>> fun = ArrayList::new;
        List<Student> apply = fun.apply(10);
        System.out.println(apply.add(new Student()));
        System.out.println(apply);

    }

}
