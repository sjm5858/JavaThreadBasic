package com.sjm.threadimpl.f2;

/**
 * @author sjm5858@126.com
 * @date 2021/4/16 23:19
 */
public class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThread.run()");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }
}
