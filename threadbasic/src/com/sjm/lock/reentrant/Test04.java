package com.sjm.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的可重入性
 *
 * @author sjm5858@126.com
 * @date 2020/12/30 20:00
 */
public class Test04 {
    static class SubThread extends Thread {
        private static Lock lock = new ReentrantLock();
        private static int num = 0; // 定义变量

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    // 可重入锁指可以反复获得该锁
                    lock.lock();
                    lock.lock();
                    num++;
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(SubThread.num);
    }
}
