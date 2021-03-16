package com.sjm.threadmethod.p1currentthread;

/**
 * 测试当前线程
 * @author sjm5858@126.com
 * @date 2020/12/21 20:18
 */
public class Test01CurrentThread {
    public static void main(String[] args) {
        System.out.println("main方法打印当前线程名称：" + Thread.currentThread().getName());

        // 创建子线程,调用SubThread1()构造方法，在main线程中调用构造方法，所以构造
        // 方法中的当前线程就是main线程
        SubThread1 t1 = new SubThread1();
//        t1.start(); // 启动子线程,子线程会调用run方法，所以run方法中的当前线程就是子线程Thread-0
        t1.run(); // 在main方法中直接调用run方法，没有开启新线程，所以在run方法中的当前线程就是main线程
    }
}
