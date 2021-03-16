package com.sjm.threadpool;

import java.util.concurrent.*;

/**
 * 演示线程池可能会吃掉程序中的异常
 *
 * @author sjm5858@126.com
 * @date 2021/1/2 18:30
 */
public class Test08 {
    private static class TraceThreadPollExecutor extends ThreadPoolExecutor{
        public TraceThreadPollExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        // 定义方法，对执行的任务进行包装，接收两个参数，第一个参数接收要执行的任务，第二个参数是一个Exception异常
        public Runnable wrap(Runnable task,Exception exception){
            return ()->{
                try {
                    task.run();
                }catch (Exception e){
                    exception.printStackTrace();
                    throw e;
                }
            };
        }

        // 重写submit方法

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task,new Exception("客户跟踪异常")));
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command,new Exception("客户跟踪异常")));
        }
    }

    // 定义一个类，用于计算两个数相除
    private static class DivideTask implements Runnable {
        private int x;
        private int y;

        public DivideTask(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "计算：" + x + "/" + y + "=" + x / y);
        }
    }

    public static void main(String[] args) {
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        // 使用自定义的线程池
        ThreadPoolExecutor poolExecutor = new TraceThreadPollExecutor(0, Integer.MAX_VALUE, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        // 向线程池中添加计算两个数相除的任务
        for (int i = 0; i < 5; i++) {
            poolExecutor.submit(new DivideTask(10, i));
        }
        /*
            运行程序，只有4条计算结果，我们实际上向线程池提交了5个计算任务，
            分析结果发现当i==0时，提交的任务会产生算术异常，线程池把该异常吃掉了，
            导致我们对该异常一无所知
            解决方法：
                1）把submit()提交方法改为execute()；
                2）对线程池进行扩展，对submit()方法进行包装
         */


    }
}
