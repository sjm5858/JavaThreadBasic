package com.sjm.intrinsiclock;

/**
 * synchronized同步代码块
 * 使用一个常量对象作为锁对象
 * @author sjm5858@126.com
 * @date 2020/12/23 19:57
 */
public class Test03 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建Test01对象，通过对象名调用mm()方法

        Test03 obj = new Test03();
        Test03 obj2 = new Test03();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm(); // 使用的锁对象OBJ常量
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.mm(); // 使用的锁对象OBJ常量
            }
        }).start();
    }

    public static final Object OBJ = new Object(); // 定义一个常量

    // 定义方法，打印100行字符串
    public void mm() {
        synchronized (OBJ) {// 使用一个常量对象作为锁对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }
}
