package com.xuan.lambda;

import com.xuan.MyClass;
import com.xuan.MyInterface1;
import com.xuan.MyInterface2;
import org.junit.Test;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/2 11:55  八月
 * @Description:
 *       java8 接口提供default 修饰的默认方法 和static 修饰的静态方法
 *              类优先原则  默认方法和类同名,被子类继承和实现时,类优先
 *              多个接口默认方法同名，必须重写方法。
 *
 * @ModifyBy:
 */
public class InterfaceTest {

    @Test
    public void test(){
        SubClass1 subClass1 = new SubClass1();
        String name = subClass1.getName();
        System.out.println(name);

        System.out.println(new SubClass2().getName());

        MyInterface1.say();
    }
    public class SubClass1 extends MyClass implements MyInterface1{


    }
    public class SubClass2 implements MyInterface1,MyInterface2 {
        @Override
        public String getName() {
            return MyInterface1.super.getName();
        }
    }

}
