package com.sjm.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用tryLock可以避免死锁
 * @author sjm5858@126.com
 * @date 2020/12/30 21:19
 */
public class Test09 {
    static class IntLock implements Runnable{
        private static ReentrantLock lock1 = new ReentrantLock();
        private static ReentrantLock lock2 = new ReentrantLock();
        private int lockNum;

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            if (lockNum % 2 == 0){
                while (true){
                    try {
                        if (lock1.tryLock()){
                            System.out.println(Thread.currentThread().getName() + "获得锁1，还想获得锁2");
                            Thread.sleep(new Random().nextInt(3));
                        }
                        if (lock2.tryLock()){
                            System.out.println(Thread.currentThread().getName() + "同时获得锁1与锁2 -- 完成任务了");
                            return; // 结束run方法执行，即当前线程结束
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock1.isHeldByCurrentThread()){
                            lock1.unlock();
                        }
                        if (lock2.isHeldByCurrentThread()){
                            lock2.unlock();
                        }
                    }
                }
            }else{
                while (true){
                    try {
                        if (lock2.tryLock()){
                            System.out.println(Thread.currentThread().getName() + "获得锁2，还想获得锁1");
                            Thread.sleep(new Random().nextInt(3));
                        }
                        if (lock1.tryLock()){
                            System.out.println(Thread.currentThread().getName() + "同时获得锁1与锁2 -- 完成任务了");
                            return; // 结束run方法执行，即当前线程结束
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock1.isHeldByCurrentThread()){
                            lock1.unlock();
                        }
                        if (lock2.isHeldByCurrentThread()){
                            lock2.unlock();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        IntLock intLock1 = new IntLock(1);
        IntLock intLock2 = new IntLock(2);
        new Thread(intLock1).start();
        new Thread(intLock2).start();
        // 运行后，使用tryLock方法()尝试获得锁，不会傻傻地等待，通过循环不停的尝，如果等待的时间足够长，线程总是会获得想到的资源。
    }
}
