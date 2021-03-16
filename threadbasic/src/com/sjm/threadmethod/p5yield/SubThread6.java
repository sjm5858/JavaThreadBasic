package com.sjm.threadmethod.p5yield;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class SubThread6 extends Thread{

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum += i;
            Thread.yield();
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin));
    }
}
