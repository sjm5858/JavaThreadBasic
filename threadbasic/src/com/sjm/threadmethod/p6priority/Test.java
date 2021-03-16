package com.sjm.threadmethod.p6priority;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:47
 */
public class Test {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setPriority(1);
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.setPriority(10);
        threadB.start();
    }
}
