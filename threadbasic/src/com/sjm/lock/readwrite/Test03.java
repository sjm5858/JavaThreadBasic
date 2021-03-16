package com.sjm.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock的读写互斥
 *   一个线程获得读锁时，写线程等待；一个线程获得写锁时，其他线程等待
 * @author sjm5858@126.com
 * @date 2021/1/1 14:50
 */
public class Test03 {
    static class Service{
        // 先定义读写锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
        // 定义方法修改数据
        public void write(){
            try {
                writeLock.lock(); // 申请获得写锁
                System.out.println(Thread.currentThread().getName() + "获得写锁，开始修改数据的时间-- " +
                        System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);// 模拟修改数据用时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "完毕的时间-- " +
                        System.currentTimeMillis());
                writeLock.unlock();// 释放读锁
            }

        }
        // 定义方法读取数据
        public void read(){
            try {
                readLock.lock(); // 获得读锁
                System.out.println(Thread.currentThread().getName() + "获得读锁，开始读取数据的时间-- " +
                        System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);// 模拟读取数据用时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();// 释放读锁
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }).start();
    }
}
