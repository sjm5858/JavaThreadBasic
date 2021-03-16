package com.sjm.lock.method;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean isFair() 判断是否为公平锁
 * boolean isHeldByCurrentThread() 判断当前线程是否持有该锁
 *
 * @author sjm5858@126.com
 * @date 2021/1/1 13:55
 */
public class Test07 {
    static class Service {
        private ReentrantLock lock;

        // 通过构造方法接收布尔值，确定当前锁是否公平
        public Service(boolean isFair) {
            this.lock = new ReentrantLock(isFair);
        }

        public void serviceMethod() {
            try {
                System.out.println("是否公平锁？" + lock.isFair() + " -- " +
                        Thread.currentThread().getName() + "调用lock前是否持有锁?" +
                        lock.isHeldByCurrentThread());
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "调用lock前是否持有锁?" + lock.isHeldByCurrentThread());
            } finally {
                if (lock.isHeldByCurrentThread())// 如果锁对象被当前线程持有就释放锁
                    lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt();
                new Service(num % 2 == 0).serviceMethod();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(runnable, "thread - " + i).start();
        }
    }
}
