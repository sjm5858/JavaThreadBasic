package com.sjm.wait;

/**
 * wait(long)的使用
 * @author sjm5858@126.com
 * @date 2020/12/26 20:05
 */
public class Test07 {
    public static void main(String[] args) {
        final Object obj = new Object();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try {
                        System.out.println("thread begin wait");
                        obj.wait(4000);
                        System.out.println("thread end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        t.start();
    }
}
