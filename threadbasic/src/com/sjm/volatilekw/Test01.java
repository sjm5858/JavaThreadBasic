package com.sjm.volatilekw;

/**
 * @author sjm5858@126.com
 * @date 2020/12/23 22:02
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        // 创建PrintString对象
        PrintString printString = new PrintString();
        // 调用方法打印字符串
        printString.printStringMethod();

        // main线程睡眠1000毫秒
        Thread.sleep(1000);
        System.out.println("在main线程中修改打印标志");
        printString.setContinuePrint(false);
        // 修改完打印标志后，运行程序，查看程序运行结果
        // 程序根本不会停止，因为printString.printStringMethod();方法调用后，该方法一处于死循环状态，程序根本就执行不到
        // printString.setContinuePrint(false);语句

    }

    // 定义类打印字符串
    static class PrintString{
        private boolean continuePrint = true;

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }

        public void printStringMethod(){
            while (continuePrint){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
