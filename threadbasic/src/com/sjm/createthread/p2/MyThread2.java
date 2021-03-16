package com.sjm.createthread.p2;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 19:40
 */
public class MyThread2 extends Thread {
    @Override

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread:" + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
