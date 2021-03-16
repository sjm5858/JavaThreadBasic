package com.sjm.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁
 * @author sjm5858@126.com
 * @date 2021/1/1 13:03
 */
public class Test01 {
//    static ReentrantLock lock = new ReentrantLock();
    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + "获得了锁对象");
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        /*
            运行程序
                1）如果是非公平锁，系统倾向于让一个线程再次获得已经持有的锁，这种分配策略是高效的，非公平的
                2）如果是公平锁，多个线程不会发生同一个线程连续多次获得锁的可能，保证了公平性
         */
    }
}
