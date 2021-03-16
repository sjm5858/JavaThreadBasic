package com.sjm.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 读写锁可以实现多个线程同时读取共享数据，即读读共享，可以提高程序读取数据的效率
 * @author sjm5858@126.com
 * @date 2021/1/1 14:41
 */
public class Test01 {
    static class Service{
        // 先定义读写锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        // 定义方法读取数据
        public void read(){
            try {
                readWriteLock.readLock().lock(); // 获得读锁
                System.out.println(Thread.currentThread().getName() + "获得读锁，开始读取数据的时间-- " +
                        System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);// 模拟读取数据用时
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();// 释放读锁
            }

        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        // 创建5个线程，调用read()方法
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.read();
                }
            }).start();
        }
        // 运行程序后，这多个线程几乎可以同时获得读锁，执行lock后面的代码。
    }
}
