package com.sjm.createthread.p1;

/**
 * 1)定义一个类继承Thread
 * @author sjm5858@126.com
 * @date 2020/12/21 19:40
 */
public class MyThread extends Thread {
    // 2) 重写Thread父类中的run()
    // run()方法体中的代码就是子线程要执行的任务

    @Override
    public void run() {
        System.out.println("这是子线程打印的。。。。");
    }
}
