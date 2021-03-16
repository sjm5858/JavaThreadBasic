package com.sjm.threadmethod.p2isAlive;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:02
 */
public class SubThread3 extends Thread {

    @Override
    public void run() {
        System.out.println("run 方法，isAlive = " + this.isAlive());
    }
}
