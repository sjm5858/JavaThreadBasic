package com.sjm.threadsafe;

/**
 * 演示线程的原子性问题
 * @author sjm5858@126.com
 * @date 2020/12/22 20:10
 */
public class Test01 {
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

    static class MyInt{
        int num;
        public int getNum(){
            return num++;
            /*
                ++自增操作的实现步骤：
                    读取num的值
                    num自增
                    把自增后的值再赋值给num变量
             */
        }
    }
}
