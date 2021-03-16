package com.sjm.createthread.p3;

/**
 * 测试实现Runnable接口的形式创建线程
 * @author sjm5858@126.com
 * @date 2020/12/21 20:02
 */
public class Test {

    public static void main(String[] args) {

        // 3)创建Runnable接口实现类对象
        MyRunnable runnable = new MyRunnable();
        // 4)创建线程对象
        Thread thread = new Thread(runnable);
        // 5)开启线程
        thread.start();
        for (int i = 1; i <= 100; i++) {
            System.out.println("main --> " + i);
        }

        // 有时调用Thread（Runnable）构造方法时，实参也会传递匿名内部类对象
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    System.out.println("sub2--------->" + i);
                }
            }
        });
        thread2.start();
    }
}
