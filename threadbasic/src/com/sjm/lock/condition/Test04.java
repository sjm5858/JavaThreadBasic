package com.sjm.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现生产者消费者设计模式，多对多，即有多个线程打印---，有多个线程打印***,实现交替打印
 *
 * @author sjm5858@126.com
 * @date 2021/1/1 12:40
 */
public class Test04 {
    static class MyService {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private boolean flag = true; // 定义交替打印标志

        // 定义方法只打印---横线
        public void printOne() {
            try {
                lock.lock();
                while (flag) {// 当flag为true等待
                    System.out.println(Thread.currentThread().getName() + "waiting");
                    condition.await();
                }
                // flag为false时打印
                System.out.println(Thread.currentThread().getName() + " -------------------------- ");
                flag = true; // 修改交替打印标志
//                condition.signal(); // 通知另外的线程打印
                condition.signalAll(); // 通知另外所有的线程打印
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 定义方法只打印***
        public void printTwo() {
            try {
                lock.lock();
                while (!flag) {// 当flag为false等待
                    System.out.println(Thread.currentThread().getName() + "waiting");
                    condition.await();
                }
                // flag为true时打印
                System.out.println(Thread.currentThread().getName() + "****************************");
                flag = false; // 修改交替打印标志
//                condition.signal(); // 通知另外的线程打印
                condition.signalAll(); // 通知另外所有的线程打印
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyService();
        for (int i = 0; i < 10; i++) {
            // 创建线程打印--
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        myService.printOne();
                    }
                }
            }).start();
            // 创建线程打印**
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        myService.printTwo();
                    }
                }
            }).start();
        }
    }
}
