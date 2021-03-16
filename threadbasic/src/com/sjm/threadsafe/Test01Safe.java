package com.sjm.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示线程的原子性问题
 * @author sjm5858@126.com
 * @date 2020/12/22 20:10
 */
public class Test01Safe {
    public static void main(String[] args) {

        // 启动两个线程，不断调用getNum()方法
        MyInt myInt = new MyInt();
        for (int  i = 1;  i <= 2 ;  i++) {
           new Thread(new Runnable() {
               @Override
               public void run() {
                  while (true){
                      System.out.println(Thread.currentThread().getName() + " -> " +
                              myInt.getNum());
                      try {
                          Thread.sleep(100);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
               }
           }).start();
        }
    }

    // 在java中提供了一个线程安全的类，保证操作的原子性
    static class MyInt{
      AtomicInteger num =  new AtomicInteger();
        public int getNum(){
            return num.getAndIncrement();
        }
    }
}
