package com.sjm.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()
 * 当锁对象没有被其他线程持有的情况下才会获得该锁定
 *
 * @author sjm5858@126.com
 * @date 2020/12/30 21:13
 */
public class Test08 {
    static class Service {
        private ReentrantLock lock = new ReentrantLock();

        public void serviceMethod() {
            try {
                if (lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + "获得锁定");
                    Thread.sleep(3000);
                } else {
                    System.out.println(Thread.currentThread().getName() + "没有获得锁定");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        Thread.sleep(50);
        Thread t2 = new Thread(r);
        t2.start();

    }
}
