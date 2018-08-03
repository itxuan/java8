package com.xuan.lambda;

import com.xuan.stream.ForkJoinCalculate;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/2 09:48  八月
 * @Description:
 * @ModifyBy:
 */
public class ForkJoinTest {

    @Test
    public void test1(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,1000000000L);
        Long invoke = pool.invoke(task);
        System.out.println(invoke);
        Instant end = Instant.now();
        long seconds = Duration.between(start, end).toMillis();
        System.out.println(seconds);
    }

    @Test
    public void test2(){

        Instant start = Instant.now();
        long sum = 0L;
        for (long item =0; item <= 1000000000L;item++){
            for (long j = 0; j <= 10; j++) {
                sum += (item * j);
            }
        }
        System.out.println(sum);
        Instant end = Instant.now();
        long millis = Duration.between(start, end).toMillis();
        System.out.println(millis);
    }
}
