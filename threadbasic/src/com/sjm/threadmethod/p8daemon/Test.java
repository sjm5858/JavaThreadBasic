package com.sjm.threadmethod.p8daemon;

/**
 * 设置线程为守护线程
 * @author sjm5858@126.com
 * @date 2020/12/21 22:04
 */
public class Test {
    public static void main(String[] args) {
        SubDaemonThread thread = new SubDaemonThread();
        // 设置线程为守护线程
        thread.setDaemon(true);
        thread.start();

        // 当前线程为main线程
        for (int i = 0; i < 10; i++) {
            System.out.println("main....");
        }
        // 当main线程结束，守护线程thread也销毁了
    }
}
