package com.sjm.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展线程池
 *
 * @author sjm5858@126.com
 * @date 2021/1/2 16:16
 */
public class Test06 {
    // 定义任务类
    private static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "任务正在被线程 " + Thread.currentThread().getId() + " 执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 定义扩展线程池，可以定义线程池类继承ThreadPoolExecutor，在子类中重写beforeExecute与afterExecute方法
        // 也可以直接使用ThreadPoolExecutor的内部类
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {
            // 在内部类中重写任务开始方法
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getName() + "线程准备执行任务:" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask) r).name + "任务执行完毕");
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        // 向线程池中添加任务
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("task-" + i);
            executorService.execute(task);
        }

        // 关闭线程池
        // 关闭线程池仅仅是说线程池不再接收新的任务，线程池中已接收的任务正常执行完毕
        executorService.shutdown();
    }
}
