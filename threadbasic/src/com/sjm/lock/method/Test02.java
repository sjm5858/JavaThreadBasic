package com.sjm.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * int getHoldCount()  返回当前线程调用lock方法的次数
 * @author sjm5858@126.com
 * @date 2021/1/1 13:14
 */
public class Test02 {
    static ReentrantLock lock = new ReentrantLock();

    public static void m1(){
        try {
            lock.lock();
            // 打印线程调用lock的次数
            System.out.println(Thread.currentThread().getName() + " -- hold count : " + lock.getHoldCount());
            // 调用m2方法，ReentrantLock是可重入锁，在m2方法可以再次获得该锁对象
            m2();
        } finally {
            lock.unlock();
        }
    }
    public static void m2(){
        try {
            lock.lock();
            // 打印线程调用lock的次数
            System.out.println(Thread.currentThread().getName() + " == hold count : " + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // main线程调用m1
        m1();
    }
}
