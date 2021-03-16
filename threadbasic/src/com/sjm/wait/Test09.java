package com.sjm.wait;

/**
 * notify() 通知过早 ,就不让线程等待了
 * // 实际上，调用start就是告诉线程调度器，当前线程准备就绪，线程调度器在什么时候开启这个线程不确定，即调用start方法的顺序，并不一定就是线程实际开启的顺序。
 * // 在当前示例中，t1等待后让t2线程唤醒，如果t2线程先唤醒了，就不让t1线程等待了
 *
 * @author sjm5858@126.com
 * @date 2020/12/26 20:10
 */
public class Test09 {
    static boolean isFirst = true; // 定义静态变量作为是否第一个运行的线标志

    public static void main(String[] args) {
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (isFirst) {
                        try {
                            System.out.println("thread begin wait");
                            lock.wait();
                            System.out.println("thread end wait");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("thread begin notify");
                    lock.notify();
                    System.out.println("thread end notify");
                    isFirst = false ; // 通知后，就把第一个线程标志修改为false
                }
            }
        });

        t2.start();
        t1.start();


    }
}
