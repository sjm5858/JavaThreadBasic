package com.sjm.test;

/**
 * @author sjm5858
 * @date 2021/4/25 9:27
 */
public class Thread6 extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "线程运行开始!");
    }
}
