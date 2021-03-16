package com.sjm.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock(long timeout, TimeUnit unit)的基本使用
 * @author sjm5858@126.com
 * @date 2020/12/30 21:05
 */
public class Test07 {
    static class TimeLock implements Runnable{
        private static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)){
                    System.out.println(Thread.currentThread().getName() + "获得锁，执行耗时任务");
                    Thread.sleep(3000);
                }else {
                    System.out.println(Thread.currentThread().getName() + "没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();

        new Thread(timeLock).start();
        new Thread(timeLock).start();
    }
}
