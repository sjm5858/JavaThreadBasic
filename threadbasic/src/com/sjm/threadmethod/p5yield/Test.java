package com.sjm.threadmethod.p5yield;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:34
 */
public class Test {
    public static void main(String[] args) {
        // 开启一个子线程，计算累加和
        SubThread6 t6 = new SubThread6();
        t6.start();

        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("main用时：" + (end - begin));

    }
}
