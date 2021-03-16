package com.sjm.threadmethod.p7interrupt;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class SubThread2 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            // 判断线程的中断标志
            if(this.isInterrupted()){
                System.out.println("当前线程的中断标志为true，我要退出了");
                return;
            }
            System.out.println("sub thread --> " + i);
        }
    }
}
