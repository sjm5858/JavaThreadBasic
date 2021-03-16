package com.sjm.threadmethod.p4getid;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class SubThread5 extends Thread{

    @Override
    public void run() {
        System.out.println("thread name = " + Thread.currentThread().getName() +
                ",id = " + this.getId());
    }
}
