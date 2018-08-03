package com.xuan.stream;

import java.util.concurrent.RecursiveTask;
import java.util.function.BiFunction;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/2 09:49  八月
 * @Description: fork -join 模式
 * @ModifyBy:
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    private long start;
    private long end;
    private static final long criticalValue = 10000L;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        if (end - start > criticalValue) {
            // 计算任务
            long sum = 0L;
            for (long item = start; item <= end; item++) {
                for (long j = 0; j <= 10; j++) {
                    sum += (item * j);
                }
            }
            return sum;
        } else {
            //拆分任务
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();

            ForkJoinCalculate right = new ForkJoinCalculate(middle - 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
