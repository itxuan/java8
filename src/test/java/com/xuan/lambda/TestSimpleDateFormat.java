package com.xuan.lambda;

import com.xuan.time.ThreadLocalDateFormat;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/3 09:27  八月
 * @Description:
 * @ModifyBy:
 */
public class TestSimpleDateFormat {

    @Test
    public void test1() throws ExecutionException, InterruptedException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
//                return sdf.parse("2018-08-03");
                return ThreadLocalDateFormat.format("2018-01-01");
            }

        };

        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(executorService.submit(task));
        }
        for (Future<Date> future:list) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }


    @Test
    public void test2() throws ExecutionException, InterruptedException {

        // 指定format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {

                return  LocalDate.parse("2018-01-01", dtf);
            }
        };

        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(executorService.submit(task));
        }
        for (Future<LocalDate> future:list) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }

    @Test
    public void test3(){
//        LocalDate  LocalDateTime LocalTime

        Instant instant = Instant.now();
        System.out.println(instant);

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // Duration 计算时间之间的间隔
        //Period  计算两个日期之间的间隔
        Instant start = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between.toMillis());

        System.out.println("----------------------------");
        LocalDate ld1 = LocalDate.of(1995,1,1);
        LocalDate ld2 = LocalDate.now();

        System.out.println(Period.between(ld1,ld2));
    }

    @Test
    public void test4(){

        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt1 = ldt.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(ldt1);
    }

    @Test
    public void test5(){

        ZoneId.getAvailableZoneIds().forEach(System.out::println);

        LocalDateTime shanghai = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(shanghai);

        //ZonedDateTime  ZonedDate    ZonedTime
        ZonedDateTime zdt  = LocalDateTime.now(ZoneId.of("Asia/Shanghai")).
                atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);
    }
}
