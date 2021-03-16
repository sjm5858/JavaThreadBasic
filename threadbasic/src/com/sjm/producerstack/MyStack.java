package com.sjm.producerstack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sjm5858@126.com
 * @date 2020/12/26 21:28
 */
public class MyStack {
    // 定义集合模拟栈
    private List list = new ArrayList();
    // 集合的最大容量
    private static final int MAX = 10;

    // 定义方法模拟入栈
    public synchronized void push() {
        // 当栈中的数据已满 就等待
        while (list.size() >= MAX) {
            System.out.println(Thread.currentThread().getName() + "begin wait ...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = "data -- " + Math.random();
        System.out.println(Thread.currentThread().getName() + "添加了数据：" + data);
        list.add(data);
        this.notifyAll();
    }

    // 定义方法模拟出栈
    public synchronized void pop() {
        while (list.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "begin wait ...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "出栈数据：" + list.remove(0));
        this.notifyAll();
    }
}
