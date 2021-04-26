package com.sjm.test;

/**
 * @author sjm5858
 * @date 2021/4/25 9:26
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "线程运行开始!");
        Thread6 thread1 = new Thread6();
        thread1.setName("线程 B");
        thread1.start();
        thread1.join();
        short s1 = 1;
        s1 = (short) (s1 + 1);
        System.out.println(s1);
        System.out.println("这时 thread1 执行完毕之后才能执行主线程");

        String m = "hello,world";
        String n = "hello,world";
        String u = new String(m);
        String v = new String("hello,world");

        System.out.println(m == n); //true
        System.out.println(m == u); //false
        System.out.println(m == v); //false
        System.out.println(u == v); //false
    }
}
