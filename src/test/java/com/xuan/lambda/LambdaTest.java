package com.xuan.lambda;

import com.xuan.lambda.compare.StudentComparator;
import com.xuan.lambda.enums.GenderEnum;
import com.xuan.lambda.model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/30 15:04  七月
 * @Description:
 *              Lambda 表达式基本语法：
 *                  ( 可选参数[params] ) -> { 功能代码 statement }
 *                  (只有一个参数 小括号可以省略，函数体只有一条语句 大括号可以省略，如果有返回值，并且函数体只有一条语句，return 可以省略)
 *                  参数的类型可以不写，JVM虚拟机可以根据上下文推断类型，"类型推断"
 *              语法格式一: 无参数、无返回值
 *                  () -> System.out.println("xxx");
 *              语法格式二: 有一个参数,无返回值
 *                  (x) -> System.out.println(x);
 *
 *              语法格式三: 多个参数，Lambda体多条执行语句 有返回值
 *                  ( x ,y) -> {
 *                      System.out.println("语句一");
 *                      return Integer.compare(x,y);
 *                  };
 *              语法格式四: 多个参数 Lambda体一条执行语句 有返回值，return 可以省略
 *
 *
 * @ModifyBy:
 */
public class LambdaTest {

    private List<Student> studentList(){
        List<Student> list = new ArrayList<>();

        list.add(new Student(1,"凌音",20,GenderEnum.WOMAN));
        list.add(new Student(2,"思颖",25,GenderEnum.WOMAN));
        list.add(new Student(1,"权贵",30,GenderEnum.MAN));
        list.add(new Student(1,"乾坤",24,GenderEnum.MAN));
        return list;
    }
    @Test
    public void test(){

        studentList()
                .stream()
                .filter((student) -> student.getAge()>24)
                .distinct()
                .limit(2)
                .sorted(new StudentComparator())
                .forEach(System.out::println);

        System.out.println("--------------------分割线--------------------------");

        studentList()
                .stream()
                .map(Student::getName)
                .forEach(System.out::println);
        System.out.println("--------------------分割线--------------------------");

        System.out.println(studentList().stream().count());
    }

    // 语法格式一 demo
    @Test
    public void test1(){

        final int num = 0;
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        }.run();

        System.out.println("--------------------分割线--------------------------");

        Runnable runnable = () -> System.out.println("Hello Lambda!" + num );
        runnable.run();
    }
    // 语法格式二 demo
    @Test
    public void test2(){

        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("被打印的字符串");

    }
    // 语法格式三 demo
    @Test
    public void test3(){

        Comparator<Integer> comparator = ( x ,y) -> {
            System.out.println("语句一");
            return Integer.compare(x,y);
        };
        System.out.println(comparator.compare(11,04));
    }
    // 语法格式四 demo
    @Test
    public void test4(){

        Comparator<Integer> comparator = (x,y) -> Math.abs(x-y);
        System.out.println(comparator.compare(10,12));
    }

    @Test
    public void  test5(){

        System.out.println(option(10L,20L,(x,y) -> x * y));
        System.out.println(option(10L,20L,(x,y) -> x + y));

    }
    // 自定义泛型函数式接口，处理两个Long 的相加 与相乘
    public Long option(Long l1,Long l2, MyFunction<Long,Long> myFunction){
        return myFunction.accept(l1,l2);
    }
}
