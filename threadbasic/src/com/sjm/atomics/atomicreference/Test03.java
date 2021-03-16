package com.sjm.atomics.atomicreference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference 原子类可以解决CAS中的ABA问题
 * 在AtomicStampedReference原子类中有一个整数标记值stamp，每次执行CAS操作时，需要对比它的版本，即比较stamp的值
 *
 * @author sjm5858@126.com
 * @date 2020/12/25 20:38
 */
public class Test03 {
    //    private static AtomicReference<String> atomicReference = new AtomicReference<>("abc");
    // 定义AtomicStampedReference引用操作abc字符串，指定初始化版本号为0
    private static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("abc", 0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stampedReference.compareAndSet("abc", "def", stampedReference.getStamp(), stampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName() + "--" + stampedReference.getReference());
                stampedReference.compareAndSet("def", "abc", stampedReference.getStamp(), stampedReference.getStamp() + 1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = stampedReference.getStamp(); // 获得版本号
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(stampedReference.compareAndSet("abc", "ggg", stamp, stamp + 1));
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(stampedReference.getReference());
    }
}
