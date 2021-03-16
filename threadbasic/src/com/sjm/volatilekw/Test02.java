package com.sjm.volatilekw;

/**
 * volatile的作用可以强制线程从公共内存中读取变量的值，而不是从工作内存中读取
 * @author sjm5858@126.com
 * @date 2020/12/23 22:02
 */
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        // 创建PrintString对象
        PrintString printString = new PrintString();
        // 开启子线程，让子线程执行PrintString对象的printStringMethod方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printStringMethod();
            }
        }).start();

        // main线程睡眠1000毫秒
        Thread.sleep(1000);
        System.out.println("在main线程中修改打印标志");
        printString.setContinuePrint(false);
        // 修改完打印标志后，运行程序，查看程序运行结果
        // 程序运行后，可能会出现死循环情况
        // 分析原因：main线程修改了printString对象的打印标志后，子线程读不到
        // 解决方法：使用volatile关键字修饰printString对象的打印标志
        //      volatile的作用可以强制线程从公共内存中读取变量的值，而不是从工作内存中读取
    }

    // 定义类打印字符串
    static class PrintString{
        private volatile boolean continuePrint = true;

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }

        public void printStringMethod(){
            System.out.println(Thread.currentThread().getName() + "开始...");
            while (continuePrint){
//                System.out.println("sub thread...");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println(Thread.currentThread().getName() + "结束...");

        }
    }
}
