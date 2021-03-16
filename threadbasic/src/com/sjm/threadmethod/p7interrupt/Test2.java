package com.sjm.threadmethod.p7interrupt;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:55
 */
public class Test2 {
    public static void main(String[] args) {
        SubThread2 t1 = new SubThread2();
        t1.start();

        // 当前线程是main线程
        for (int i = 0; i < 100; i++) {
            System.out.println("main == >" + i);
        }
        // 中断子线程
        t1.interrupt();
    }
}
