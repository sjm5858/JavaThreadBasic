package com.sjm.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean isLocked()  查询当前锁是否被线程持有
 * @author sjm5858@126.com
 * @date 2021/1/1 14:03
 */
public class Test08 {
    static ReentrantLock lock = new ReentrantLock();

    static void sm(){
        try {
            System.out.println(" before lock() -- " + lock.isLocked());
            lock.lock();
            System.out.println(" after lock() -- " + lock.isLocked());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("11 -- " + lock.isLocked());

        new Thread(new Runnable() {
            @Override
            public void run() {
                sm();
            }
        }).start();

        Thread.sleep(3000);
        System.out.println("22 -- " + lock.isLocked());


    }
}
