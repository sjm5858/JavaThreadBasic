package com.sjm.createthread.p3;

/**
 * 当线程已经有父类了，就不能用继承Thread类的形式创建线程，可以使用实现Runnable接口的形式
 * 1)定义类实现Runnable接口
 * @author sjm5858@126.com
 * @date 2020/12/21 20:02
 */
public class MyRunnable implements Runnable {

    // 2)重写Runnable接口中的抽象方法run(),run()方法就是子线程要执行的代码
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println("sub thread --> " + i);
        }
    }
}
