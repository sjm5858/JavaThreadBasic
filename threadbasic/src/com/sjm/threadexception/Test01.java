package com.sjm.threadexception;

import java.nio.charset.StandardCharsets;

/**
 * 演示设置线程的UncaughtExceptionHandler回调接口
 * @author sjm5858@126.com
 * @date 2021/1/1 20:56
 */
public class Test01 {
    public static void main(String[] args) {
        // 1)设置线程全局的
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // t参数接收发生异常的线程，e就是该线程中的异常
                System.out.println(t.getName() + "线程产生了异常：" + e.getMessage());
            }
        });

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(12/0);
        });
        t1.start();

        new Thread(() -> {
            String txt = null;
            System.out.println(txt.length());
        }).start();
        /*
            在实际开发中，这种设计异常处理的方式还是比较常用的，尤其是异常执行的方法
            如果线程产生了异常，JVM会调用dispatchUncaughtException()方法，
            在该方法中调用了getUncaughtExceptionHandler().uncaughtException(this, e);
            如果当前线程设置了UncaughtExceptionHandler回调接口就直接调用它自己的uncaughtException方法
            如果没有设置则调用当前线程所在线程组UncaughtExceptionHandler回调接口的uncaughtException方法
            如果线程组也没有设置回调接口，则直接把异常的栈信息定向到System.err中
         */
    }
}
