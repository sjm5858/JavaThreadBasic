package com.sjm.intrinsiclock;

/**
 * 同步方法与同步代码块如何选择
 * 同步方法，锁的粒度粗，执行效率低
 * 同步代码块，锁的粒度细，执行效率高
 * @author sjm5858@126.com
 * @date 2020/12/23 20:46
 */
public class Test07 {
    public static void main(String[] args) {

        Test07 obj = new Test07();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask();
            }
        }).start();
    }

    // 同步方法，执行效率低
    public synchronized void doLongTimeTask() {
        System.out.println("Task Begin");
        try {
            Thread.sleep(3000); // 模拟任务需要准备3秒钟
            System.out.println("开始同步");
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
            System.out.println("Task End");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 同步代码块，锁的粒度细，执行效率高
    public void doLongTimeTask2() {
        System.out.println("Task Begin");
        try {
            Thread.sleep(3000); // 模拟任务需要准备3秒钟
            synchronized (this) {
                System.out.println("开始同步");
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " --> " + i);
                }
            }
            System.out.println("Task End");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
