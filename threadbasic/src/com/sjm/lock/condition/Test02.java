package com.sjm.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个Condition实现通知部分线程，使用更灵活
 * @author sjm5858@126.com
 * @date 2021/1/1 12:29
 */
public class Test02 {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        // 定义两个Condition对象
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();

        // 定义方法，使用conditionA等待
        public void waitMethodA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " begin wait:" + System.currentTimeMillis());
                conditionA.await();
                System.out.println(Thread.currentThread().getName() + " end wait:" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 定义方法，使用conditionB等待
        public void waitMethodB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " begin wait:" + System.currentTimeMillis());
                conditionB.await();
                System.out.println(Thread.currentThread().getName() + " end wait:" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 定义方法唤醒conditionA对象上的等待
        public void signalA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " signal A time:" + System.currentTimeMillis());
                conditionA.signal();
                System.out.println(Thread.currentThread().getName() + " signal A time:" + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }

        // 定义方法唤醒conditionB对象上的等待
        public void signalB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " signal B time:" + System.currentTimeMillis());
                conditionB.signal();
                System.out.println(Thread.currentThread().getName() + " signal B time:" + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        // 开启两个线程，分别调用waitMethodA，waitMethodB方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodB();
            }
        }).start();

        Thread.sleep(3000);
        // 唤醒conditionA对象上的等待,conditionB上的等待依然在等待。
        service.signalA();

    }
}
