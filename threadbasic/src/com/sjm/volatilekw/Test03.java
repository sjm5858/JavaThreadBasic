package com.sjm.volatilekw;

/**
 * volatile不具备原子性
 * @author sjm5858@126.com
 * @date 2020/12/23 22:44
 */
public class Test03 {
    public static void main(String[] args) {
        // 在main线程中创建10个子线程
        for (int i = 0; i < 10; i++) {
            new MyThread().start();
        }

    }

    static class MyThread extends Thread {
        // volatile关键字仅仅表示所有线程从主内存中读取count的值
//        volatile public static int count;
        public static int count;

//        public static void addCount() {
//            for (int i = 0; i < 1000; i++) {
//                count++;
//            }
//            System.out.println(Thread.currentThread().getName() + "count = " + count);
//        }

        public synchronized static void addCount() {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
