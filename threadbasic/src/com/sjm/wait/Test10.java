package com.sjm.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * wait等待的条件发生了变化
 * 定义一个集合
 * 定义一个线程向集合中添加数据，添加完数据后通知另外的线程从集合中取数据
 * 定义一个线程从集合中取数据，如果集合中没有数据就等待
 *
 * @author sjm5858@126.com
 * @date 2020/12/26 20:24
 */
public class Test10 {
    public static void main(String[] args) {
        // 定义添加数据的线程对象
        ThreadAdd threadAdd = new ThreadAdd();
        // 定义取数据的线程对象
        ThreadSubtract threadSubtract = new ThreadSubtract();
        threadSubtract.setName("subtract 1");

        // 测试一：先开启添加数据的线程，再开启一个取数据的线程，大多数情况下会正常取数据
//        threadAdd.start();
//        threadSubtract.start();
        // 测试2：先开启取数据的线程，再开启添加数据的线程，
//        threadSubtract.start();
//        threadAdd.start();
        // 测试3：先开启两个取数据的线程，再开启添加数据的线程，
        ThreadSubtract threadSubtract2 = new ThreadSubtract();
        threadSubtract2.setName("subtract 2");
        threadSubtract.start();
        threadSubtract2.start();
        threadAdd.start();
        /*
        某一次的执行结果如下：
        subtract 1begin wait ...
        subtract 2从集合中取了:data后，集合中数据的数量:0
        subtract 1end wait ...
        Exception in thread "subtract 1" java.lang.IndexOutOfBoundsException
        分析可能的执行顺序：
        ...
        解决方法:if 换成 while

         */

    }

    // 1）定义List集合
    static List list = new ArrayList();

    // 2）定义方法从集合中取数据
    public static void subtract() {
        synchronized (list) {
//            if (list.size() == 0) {
            while (list.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + "begin wait ...");
                    list.wait();
                    System.out.println(Thread.currentThread().getName() + "end wait ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object data = list.remove(0); // 从集合中取出一个数据
            System.out.println(Thread.currentThread().getName() + "从集合中取了:" + data + "后，集合中数据的数量:" +
                    list.size());
        }
    }

    // 3)定义方法向集合中添加数据后，通知等待的线程取数据
    public static void add() {
        synchronized (list) {
            list.add("data");
            list.notifyAll();
        }
    }

    // 4）定义线程类调用add()更新数据的方法
    static class ThreadAdd extends Thread {
        @Override
        public void run() {
            add();
        }
    }

    // 5）定义线程类调用subtract()方法
    static class ThreadSubtract extends Thread {
        @Override
        public void run() {
            subtract();
        }
    }
}
