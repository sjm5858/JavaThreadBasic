package com.sjm.threadmethod.p4getid;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:27
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() +
                ",id = " +Thread.currentThread().getId());

        // 子线程的id
        for (int i = 0; i < 20; i++) {
            new SubThread5().start();
            Thread.sleep(100);
        }
    }
}
