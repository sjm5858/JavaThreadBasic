package com.sjm.volatilekw;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类进行自增
 * @author sjm5858@126.com
 * @date 2020/12/23 22:44
 */
public class Test04 {
    public static void main(String[] args) throws InterruptedException {
        // 在main线程中创建10个子线程
        for (int i = 0; i < 100; i++) {
            new MyThread().start();
        }
        Thread.sleep(1000);
        System.out.println(MyThread.count.get());

    }

    static class MyThread extends Thread {
        private static AtomicInteger count = new AtomicInteger();

        public static void addCount() {
            for (int i = 0; i < 1000; i++) {
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName() + "count = " + count.get());
        }


        @Override
        public void run() {
            addCount();
        }
    }
}
