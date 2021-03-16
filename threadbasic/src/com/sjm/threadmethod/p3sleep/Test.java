package com.sjm.threadmethod.p3sleep;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:11
 */
public class Test {

    public static void main(String[] args) {
        SubThread4 t4 = new SubThread4();
        System.out.println("main_begin = " + System.currentTimeMillis());
//        t4.start();// 开启新的线程
        t4.run(); // 在main线程中调用实例方法run，没有开启新的线程
        System.out.println("main_end = " + System.currentTimeMillis());

    }
}
