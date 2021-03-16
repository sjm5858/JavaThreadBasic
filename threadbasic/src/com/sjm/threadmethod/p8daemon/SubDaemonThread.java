package com.sjm.threadmethod.p8daemon;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class SubDaemonThread extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println("sub thread....");
        }
    }
}
