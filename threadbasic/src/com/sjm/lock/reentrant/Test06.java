package com.sjm.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过ReentrantLock锁的lockInterruptibly()方法避免死锁的产生
 * @author sjm5858@126.com
 * @date 2020/12/30 20:28
 */
public class Test06 {
    static class IntLock implements Runnable {
        // 创建两个锁对象
        public static ReentrantLock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        int lockNum;// 定义整数变量，决定使用哪个锁

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            try {
                if (lockNum % 2 == 1) {
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁1，还需要获得锁2");
                    Thread.sleep(500);
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1和锁2...");
                } else {
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁2，还需要获得锁1");
                    Thread.sleep(500);
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1和锁2...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread())
                    lock1.unlock();
                if (lock2.isHeldByCurrentThread())
                    lock2.unlock();
                System.out.println(Thread.currentThread().getName() + "线程退出了");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);

        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);
        t1.start();
        t2.start();

        // 如果还有线程没有结束就中断线程
        Thread.sleep(3000);
//        if (t1.isAlive()){
//            t1.interrupt();
//        }
//        if (t2.isAlive()){
//            t2.interrupt();
//        }
        // 可以中断任何一个线程来解决死锁
        if (t2.isAlive()) {
            t2.interrupt();
        }

    }
}
