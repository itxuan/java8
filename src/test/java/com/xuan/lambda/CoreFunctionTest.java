package com.xuan.lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/7/31 16:11  七月
 * @Description:
 * @ModifyBy:
 */

/**
 *      Consumer<T> 消费型接口  void accept(T t)
 *      Supplier<T> 生产型接口  T get();
 *
 */
public class CoreFunctionTest {

    @Test
    public void testConsumer(){
        accept(100.00d,x -> {
            System.out.println("use $" + x + " for eating");
        });
    }
    private void accept(double money, Consumer<Double> consumer){

        consumer.accept(money);
    }

    @Test
    public void test(){

        System.out.println(supplierNumber(10, new Random()::nextLong));
        System.out.println("=================  分割线  ==================");
        supplierNumber(10,new Random()::nextLong).stream().forEach(System.out::println);
        System.out.println("=================  分割线  ==================");
        System.out.println(supplierNumber(null, new Random()::nextLong));
    }

    private List<Long> supplierNumber(Integer count,Supplier<Long> supplier){

        List<Long> list = new ArrayList<>();
        if(count == null){
            return null;
        }
        for (int i = 0 ; i < count; i ++){
            Long val = supplier.get();
            list.add(val);
        }
        return list;
    }

    @Test
    public void testFunction(){

        System.out.println(stringHandler("lowerString",x -> {return  x.toUpperCase();}));

        System.out.println(stringHandler("学校、社会",x -> x.substring(3)));
    }

    private String stringHandler(String s , Function<String,String> function){

        return function.apply(s);
    }

    //  获取 List<Integer>  的非负数的集合
    @Test
    public void testPredicate(){
        Integer[] integers = {1, -1, 2, -2, 0};
        List<Integer> list = Arrays.asList(integers);
        List<Integer> predicate = predicate(list, x -> {
            return x >= 0;
        });
        predicate.stream().forEach(System.out::println);
    }

    private List<Integer> predicate(List<Integer> numbers, Predicate<Integer> predicate){

        List<Integer> list = new ArrayList<>();
        for (Integer integer:numbers){
            if(predicate.test(integer)){
                list.add(integer);
            }
        }
        return list;
    }
}

