package com.sjm.intrinsiclock;

/**
 * synchronized同步静态方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是当前类的运行时类对象
 *
 * @author sjm5858@126.com
 * @date 2020/12/23 19:57
 */
public class Test06 {
    public static void main(String[] args) {
        //先创建Test01对象，通过对象名调用mm()方法

        Test06 obj = new Test06();

        // 一个线程调用m1方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.m1(); // 使用的锁对象是Test06.class
            }
        }).start();

        // 一个线程调用sm2方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test06.sm2(); // 使用的锁对象是Test06.class
            }
        }).start();
    }

    // 定义方法，打印100行字符串
    public void m1() {
        // 使用当前类的运行时类作为锁对象,可以简单的理解为把Test06类的字节码文件作为锁对象
        synchronized (Test06.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }

    // 使用synchronized修饰静态方法，同步静态方法，默认当前类的运行时类Test06.class作为锁对象
    public synchronized static void sm2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}
