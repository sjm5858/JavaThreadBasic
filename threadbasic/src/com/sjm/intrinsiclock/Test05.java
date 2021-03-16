package com.sjm.intrinsiclock;

/**
 * synchronized同步实例方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是this对象
 *
 * @author sjm5858@126.com
 * @date 2020/12/23 19:57
 */
public class Test05 {
    public static void main(String[] args) {
        //先创建Test01对象，通过对象名调用mm()方法

        Test05 obj = new Test05();

        // 一个线程调用mm方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm(); // 使用的锁对象this就是obj对象
            }
        }).start();

        // 一个线程调用mm22方法
        new Thread(new Runnable() {
            @Override
            public void run() {
//                obj.mm22(); // 使用的锁对象this也是obj对象,可以同步
                new Test05().mm22(); // 使用的锁对象this是刚刚new创建的一个新对象，不是同一个锁对象，不能同步
            }
        }).start();
    }

    // 定义方法，打印100行字符串
    public void mm() {
        synchronized (this) {// 经常使用this当前对象作为锁对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }

    // 使用synchronized修饰实例方法，同步实例方法，默认this作为锁对象
    public synchronized void mm22() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}
