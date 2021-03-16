package com.sjm.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 自定义拒绝策略
 *
 * @author sjm5858@126.com
 * @date 2021/1/2 10:57
 */
public class Test03 {
    public static void main(String[] args) {

        // 定义一个任务
        Runnable r = () -> {
            int num = new Random().nextInt(5);
            System.out.println(Thread.currentThread().getId() + "--" + System.currentTimeMillis() +
                    "开始睡眠" + num + "秒");
            try {
                TimeUnit.SECONDS.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // 创建线程池,自定义拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        //r就是请求的任务，executor 就是当前线程池
                        System.out.println(r + "is discarding ...");
                    }
                });

        // 向线程池提交若干任务
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            threadPoolExecutor.submit(r);
        }
    }
}
