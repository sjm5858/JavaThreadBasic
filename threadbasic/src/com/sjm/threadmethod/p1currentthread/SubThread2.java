package com.sjm.threadmethod.p1currentthread;

/**
 * 定义一个线程类
 *     分别在构造方法和run方法中打印当前线程
 * @author sjm5858@126.com
 * @date 2020/12/21 20:19
 */
public class SubThread2 extends Thread {

    public SubThread2(){
        System.out.println("构造方法中，Thread.currentThread().getName() --> " + Thread.currentThread().getName());
        System.out.println("构造方法中，this.getName() --> " + this.getName());
    }


    @Override
    public void run() {
        System.out.println("run方法中，Thread.currentThread().getName() --> " + Thread.currentThread().getName());
        System.out.println("run方法中，this.getName() --> " + this.getName());
    }
}
