package com.sjm.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean hasQueuedThread(Thread thread) 查询参数指定的线程是否在等待获得锁
 * boolean hasQueuedThreads() 查询是否还有线程在等待获得该锁
 *
 * @author sjm5858@126.com
 * @date 2021/1/1 13:35
 */
public class Test05 {
    static ReentrantLock lock = new ReentrantLock();

    public static void waitMethod(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获得了锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放了锁....");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Test05.waitMethod();
            }
        };
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].setName("thread - " + i);
            threads[i].start();
        }

        Thread.sleep(3000);

        // 判断数组中的每个线程对象是否正在等待获得锁
        System.out.println(lock.hasQueuedThread(threads[0]));
        System.out.println(lock.hasQueuedThread(threads[1]));
        System.out.println(lock.hasQueuedThread(threads[2]));
        System.out.println(lock.hasQueuedThread(threads[3]));
        System.out.println(lock.hasQueuedThread(threads[4]));

        Thread.sleep(2000);

        // 再次判断是否还有线程在等待获得该锁
        System.out.println("hasQueuedThreads:" + lock.hasQueuedThreads());
    }
}
