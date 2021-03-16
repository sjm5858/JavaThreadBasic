package com.sjm.wait;

/**
 * wait()会使线程等待
 *  需要放在同步代码块中，通过锁对象调用
 * @author sjm5858@126.com
 * @date 2020/12/25 21:46
 */
public class Test02 {
    public static void main(String[] args) {
        try {
            String text = "sjm";
            String anothre = "sjm2";
            System.out.println("同步前的代码");
            synchronized (text) {
                System.out.println("同步代码块开始");
                text.wait(); // 调用wait()方法后，当前线程就会等待，释放锁对象，当前线程需要被唤醒，如果没有唤醒就会一直等待
//                anothre.wait(); // 不是锁对象调用会产生 java.lang.IllegalMonitorStateException 异常
                System.out.println("wait后面的代码。。。");
            }
            System.out.println("同步代码块后面的代码。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main后面的其它代码。。。");
    }
}
