package com.sjm.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 演示ForkJoinPool线程池的使用
 * 使用该线程池模拟数列求和
 *
 * @author sjm5858@126.com
 * @date 2021/1/2 20:39
 */
public class Test09 {
    // 计算数列的和，需要返回结果，可以定义任务继承
    private static class CountTask extends RecursiveTask<Long> {
        // 定义数据规模的阈值，允许计算10000个数内的和，超过该阈值的数列就需要分解
        private static final int THRESHOLD = 10000;
        // 定义每次把大任务分解为100个小任务
        private static final int TASKNUM = 100;
        // 计算数列的起始值
        private long start;
        // 计算数列的结束值
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        // 重写类的compute()方法，计算数列的结果
        @Override
        protected Long compute() {
            long sum = 0;
            // 判断任务是否需要继续分解
            if (end - start < THRESHOLD) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                // 约定每次分解成100个小任务，计算每个任务的计算量
                long step = (start + end) / TASKNUM;
                // 创建一个存储任务的集合
                ArrayList<CountTask> subTaskList = new ArrayList<>();
                long pos = start; // 任务的起始位置
                for (int i = 0; i < TASKNUM; i++) {
                    long lastOne = pos + step; // 每个任务的结束位置
                    // 调整最后一个任务的结束位置
                    if (lastOne > end) {
                        lastOne = end;
                    }
                    // 创建子任务
                    CountTask task = new CountTask(pos, lastOne);
                    // 把任务添加到集合中
                    subTaskList.add(task);
                    // 调用fork提交子任务
                    task.fork();
                    // 调整下个任务的起始位置
                    pos += step + 1;
                }

                // 等待所有的子任务结束后，合并计算结果
                for (CountTask task : subTaskList) {
                    sum += task.join(); // join会一直等待子任务执行完毕返回执行结果
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        // 创建ForkJoinPool线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 创建一个大任务
        CountTask task = new CountTask(0, 200000);
        // 把大任务提交给线程池
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        Long res = null;
        try {
            res = result.get();
            System.out.println("计算数列结果为：" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 验证
        long s = 0;
        for (int i = 0; i <= 200000; i++) {
            s += i;
        }
        System.out.println(s);
    }
}
