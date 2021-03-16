package com.sjm.lock.method;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean hasWaiters(Condition condition) 查询是否有线程正在等待指定的Condition条件
 *
 * @author sjm5858@126.com
 * @date 2021/1/1 13:45
 */
public class Test06 {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition(); // 返回锁定的条件

    static void sm() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " lock ...");
            System.out.println("是否有线程正在等待当前Condition条件？" + lock.hasWaiters(condition) +
                    "; WaitQueueLength:" + lock.getWaitQueueLength(condition));
            condition.await(new Random().nextInt(1000), TimeUnit.MILLISECONDS); // 超时后会自动唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "超时唤醒，是否有线程正在等待当前Condition条件？" + lock.hasWaiters(condition) +
                    "; WaitQueueLength:" + lock.getWaitQueueLength(condition));
//            System.out.println(Thread.currentThread().getName() + " unlock ...");
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
