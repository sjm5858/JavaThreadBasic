package com.sjm.threadmethod.p6priority;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class ThreadA extends Thread{

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("A用时：" + (end - begin));

    }
}
