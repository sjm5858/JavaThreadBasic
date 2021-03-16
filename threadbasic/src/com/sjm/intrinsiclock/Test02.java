package com.sjm.intrinsiclock;

/**
 * synchronized同步代码块
 * this锁对象
 * 如果线程的锁不同，不能实现同步
 * 想要同步必须使用同一个锁对象
 *
 * @author sjm5858@126.com
 * @date 2020/12/23 19:57
 */
public class Test02 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建Test01对象，通过对象名调用mm()方法

        Test02 obj = new Test02();
        Test02 obj2 = new Test02();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.mm(); // 使用的锁对象this就是obj对象
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.mm(); // 使用的锁对象this是obj2对象
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
}
