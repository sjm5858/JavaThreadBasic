package com.sjm.threadmethod.p1currentthread;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 20:18
 */
public class Test02CurrentThread {
    public static void main(String[] args) throws InterruptedException {

        // 创建子线程对象
        SubThread2 t2 = new SubThread2();
        t2.setName("t2");
        t2.start();

        //
        Thread.sleep(500);

        // Thread()构造方法形参是Runnable接口，调用时传递的实参是接口的实现类对象
        Thread t3 = new Thread(t2);
        t3.setName("t3");
        t3.start();

    }
}
