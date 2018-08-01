package com.xuan.lambda;

import com.xuan.lambda.enums.GenderEnum;
import com.xuan.lambda.model.Student;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/1 10:08  八月
 * @Description:
 *
 * @ModifyBy:
 */
public class StreamTest {

    @Test
    public void testStream(){

        //底层封装都是StreamSupport.stream()
        Stream streams = StreamSupport.stream(Spliterators.spliterator(new Object[]{"str"},0) ,false);
        streams.forEach(System.out::println);
        // 创建流方式一  Collection
        List<Object> list = new ArrayList<>();
        Stream<Object> stream = list.stream();
        Stream<Object> objectStream = list.parallelStream();
        // collection 封装的方法  stream(x,false)      parallelStream (x,true) 并行流

        //创建方式二  通过Arrays的静态方法
        Stream<String> stream1 = Arrays.stream(new String[]{"a", "b", "c"});



        //
        //创建方式三 通过Stream.of() Stream.iterate()  Stream.generate()等静态方法
        Stream<Student> studentStream = Stream.of(new Student[]{});

        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(5)
                .forEach(System.out::println);
        System.out.println("============   分割线   ================");
        Stream.generate(Student::new).limit(3)
                .forEach(x ->System.out.println(x));
    }
    // ============================stream api 开始
    @Test
    public void test2(){

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.stream().map(x -> x.toUpperCase())
                .forEach(System.out::println);

        System.out.println("=================================");

        list.stream().map(StreamTest::filterCharacter).forEach(x -> x.forEach(System.out::println));

        System.out.println("===============分割线=================");
        //flatMap 当stream 中包含多个小的stream 会合并成一个流 如果是null 返回一个空的流
        list.stream().flatMap(StreamTest::filterCharacter).forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String s){
        Objects.requireNonNull(s);
        List<Character> list = new ArrayList<>();
        for (Character character :s.toCharArray()){
            list.add(character);
        }
        return list.stream();
    }

    /**
     *  终止操作：
     *  allMatch ----> 检查是否全部匹配
     *  anyMatch ----> 检查是否任意一个匹配
     *  noneMatch -----> 检查是否都不匹配
     *  findFirst ----> 返回流中的第一个
     *  findAny  并行去找 找出符合条件的任意一个
     *  count  返回流中的数量
     *  max 返回最大的  需要自定义排序
     *  min  返回最小的 需要自定义排序
     */
    @Test
    public void test3(){

        Optional<Student> max = list.stream().max(new StudentComparator());
        System.out.println(max);
        Optional<Student> min = list.stream().min((x, y) -> {
            if (x.getAge().equals(y.getAge())) {
                return x.getId() - y.getId();
            } else {
                return x.getAge() - y.getAge();
            }
        });
        System.out.println(min);

        // 找最小的年龄
        Optional<Integer> minAge = list.stream().map(Student::getAge)
                .min(Integer::compareTo);
        System.out.println(minAge);

    }
    @Test
    public void test4() {

        System.out.println(list.stream().allMatch( x -> x.getGender().equals(GenderEnum.WOMAN)));
        System.out.println(list.stream().findAny().get());
        boolean b = list.stream().anyMatch(x -> x.getAge() >40 );
        System.out.println(b);
        System.out.println("-------------------分割线----------------------------");
        Optional<Student> any = list.parallelStream().filter(x -> x.getGender().equals(GenderEnum.WOMAN)).findAny();
        System.out.println(any);
    }

    public class StudentComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            if(o1.getAge().equals(o2.getAge())){
                return o1.getId() - o2.getId();
            }else {
                return o1.getAge() - o2.getAge();
            }
        }
    }

    List<Student> list = Arrays.asList(new Student[]{
            new Student(1,"宣总",23,GenderEnum.MAN),
            new Student(2,"陈总",22,GenderEnum.WOMAN),
            new Student(3,"吴总",23,GenderEnum.WOMAN),
            new Student(4,"思颖",2,GenderEnum.WOMAN)
    });



    // reduce  归并 产生10个随机数 求和
    // map-reduce 模式 --> 重要 到时候再深入

    //  reduce 和 collect 参考网址: https://blog.csdn.net/piglite/article/details/53823584
    @Test
    public void test5(){

        Stream<Integer> supplier = supplier(10,100,  new Random()::nextInt );
//        Integer[] arrs = supplier.toArray(x -> new Integer[x]);
//        System.out.println(Arrays.toString(arrs));

        Integer reduce = supplier.reduce(0, (x, y) ->{
             return x + y; });
        System.out.println(reduce);

    }


    public static Stream<Integer> supplier(Integer count,Integer seed,Function<Integer,Integer> supplier){

        List<Integer> list = new ArrayList<>();
        for (int i=0; i < count ;i++){
            Integer integer = supplier.apply(seed);
            System.out.println("for  "+integer);
            list.add(integer);
        }
        return list.stream();
    }

    // 收集  收集器  collector  Collector  Collectors
    @Test
    public void test6(){

        list.stream()
                .map(Student::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        //R collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)
        //supplier：一个能创造目标类型实例的方法。
        // accumulator：一个将当元素添加到目标中的方法。
        // combiner：一个将中间状态的多个结果整合到一起的方法（并发的时候会用到 ? 这个以后再说）
        list.stream().map(Student::getAge)
                .collect(ArrayList::new ,(arrayList,element) -> arrayList.add(element),(list,list1) -> list.addAll(list1))
                .forEach(System.out::println);

        list.stream().collect(HashMap::new ,((hashMap, student) -> hashMap.put(student.getId(),student.getName())), Map::putAll)
                .forEach((k,v)->System.out.println("k = " + k +",v = " + v));
    }
}
