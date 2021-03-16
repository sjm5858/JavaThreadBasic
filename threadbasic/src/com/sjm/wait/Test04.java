package com.sjm.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * notify()不会立即释放锁对象
 * @author sjm5858@126.com
 * @date 2020/12/25 22:03
 */
public class Test04 {
    public static void main(String[] args) throws InterruptedException {
        // 定义一个List集合存储String数据
        List<String> list = new ArrayList<>();

        // 定义第一个线程，当list集合中元素的数量不等于5时线程等待
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list){
                    if (list.size() != 5){
                        System.out.println("线程1开始等待：" + System.currentTimeMillis());
                        try {
                            list.wait();// 线程等待，会释放锁对象，当前线程转入blocked阻塞状态
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程1结束等待：" + System.currentTimeMillis());
                    }
                }
            }
        });

        // 定义第二个线程，向list集合中添加元素
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(list){
                    for (int i = 1; i <= 10; i++) {
                        System.out.println("线程2添加了第" + i + "个数据");
                        list.add("data --" + i);
                        // 判断元素的数量是否满足唤醒线程1
                        if (list.size() == 5){
                            list.notify();// 唤醒线程,不会立即释放锁对象，需要等到当前同步代码块都执行完之后才能释放锁对象
                            System.out.println("线程2已经发送唤醒通知：" + System.currentTimeMillis());
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        // 为了确保t2在t1之后开启
        Thread.sleep(500);
        t2.start();
    }
}
