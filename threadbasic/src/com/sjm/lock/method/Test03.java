package com.sjm.lock.method;

import javax.swing.plaf.SliderUI;
import java.util.concurrent.locks.ReentrantLock;

/**
 * int getQueueLength() 返回正等待获得锁的线程预估数
 *
 * @author sjm5858@126.com
 * @date 2021/1/1 13:20
 */
public class Test03 {
    static ReentrantLock lock = new ReentrantLock();

    public static void sm() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁，执行方法，估计等待获得锁的线程数：" +
                    lock.getQueueLength());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Test03.sm();
            }
        };
        // 开启10个线程，执行sm方法
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
