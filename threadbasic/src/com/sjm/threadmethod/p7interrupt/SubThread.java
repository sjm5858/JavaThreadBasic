package com.sjm.threadmethod.p7interrupt;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class SubThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("sub thread --> " + i);
        }
    }
}
