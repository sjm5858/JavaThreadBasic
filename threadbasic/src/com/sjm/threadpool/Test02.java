package com.sjm.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的计划任务
 *
 * @author sjm5858@126.com
 * @date 2021/1/2 9:55
 */
public class Test02 {
    public static void main(String[] args) {
        // 创建一个有调度功能的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        // 在延迟2秒后执行任务
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getId() + "-- " + System.currentTimeMillis());
        }, 2, TimeUnit.SECONDS);

        // 以固定的频率执行任务，开启任务的时间是固定的
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getId() + "--以固定频率开启任务 " + System.currentTimeMillis());
            try {
                // 睡眠模拟任务执行时间，如果任务执行时长超过了时间间隔，则任务完成后立即开启下个任务
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 2, TimeUnit.SECONDS);

//        // 在上次任务结束后，在固定延迟后再次执行该任务，不管执行任务耗时多长，总是在任务结束后的2秒再次开启新的任务
//        scheduledExecutorService.scheduleWithFixedDelay(()->{
//            System.out.println(Thread.currentThread().getId() + "--以固定频率开启任务 " + System.currentTimeMillis());
//            try {
//                // 睡眠模拟任务执行时间，如果任务执行时长超过了时间间隔，则任务完成后立即开启下个任务
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },3,2,TimeUnit.SECONDS);
    }
}
