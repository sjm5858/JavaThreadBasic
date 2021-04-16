package com.sjm.threadimpl.f1;

/**
 * @author sjm5858@126.com
 * @date 2021/4/16 23:11
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread.run()");
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();
    }
}
