package com.sjm.atomics.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用AtomicReference原子读写一个对象
 * @author sjm5858@126.com
 * @date 2020/12/25 20:25
 */
public class Test01 {

    // 创建一个AtomicReference对象
    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");

    public static void main(String[] args) throws InterruptedException {
        // 创建10个线程修改字符串
        for (int i = 0; i < 100; i++) {
           new Thread(new Runnable() {
               @Override
               public void run() {
                  if(atomicReference.compareAndSet("abc","def")) {
                      System.out.println(Thread.currentThread().getName() + ":把字符串abc更改为def");
                  }
               }
           }) .start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(atomicReference.compareAndSet("def","abc")) {
                        System.out.println(Thread.currentThread().getName() + ":把字符串还原为abc");
                    }
                }
            }) .start();
        }

        Thread.sleep(1000);
        System.out.println(atomicReference.get());
    }
}
