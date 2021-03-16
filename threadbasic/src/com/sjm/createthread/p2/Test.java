package com.sjm.createthread.p2;

/**
 * 演示线程运行结果的随机性
 * @author sjm5858@126.com
 * @date 2020/12/21 19:51
 */
public class Test {
    public static void main(String[] args) {
        MyThread2 thread2 = new MyThread2();
        thread2.start(); // 开启子线程
        // 当前是main线程
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("main --- :" + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
