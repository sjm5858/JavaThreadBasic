package com.sjm.lock.method;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * int getWaitQueueLength(Condition condition) 返回与Condition条件相关的等待的线程的预估数
 *
 * @author sjm5858@126.com
 * @date 2021/1/1 13:26
 */
public class Test04 {
    static class Service {
        ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition(); // 返回锁给定的Condition

        public void waitMethod() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 进入等待前，现在该condition条件上等待的线程预估数：" +
                        lock.getWaitQueueLength(condition));
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void notifyMethod() {
            try {
                lock.lock();
                condition.signalAll(); // 唤醒所有的等待
                System.out.println("唤醒所有的等待后，现在该condition条件上等待的线程预估数：" +
                        lock.getWaitQueueLength(condition));
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(1000);

        // 唤醒所有的等待
        service.notifyMethod();
    }
}
