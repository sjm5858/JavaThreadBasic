package com.sjm.threadmethod.p2isAlive;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:01
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        SubThread3 t3 = new SubThread3();
        System.out.println("begin == " + t3.isAlive());
        t3.start();
        System.out.println("end == " + t3.isAlive());// 结果不一定
    }
}
