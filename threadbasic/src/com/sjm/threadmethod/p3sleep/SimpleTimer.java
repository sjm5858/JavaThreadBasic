package com.sjm.threadmethod.p3sleep;

/**
 * 使用线程休眠Thread.sleep完成一个简易的计时器
 * @author sjm5858@126.com
 * @date 2020/12/21 21:20
 */
public class SimpleTimer {
    public static void main(String[] args) {
        int remaining = 10; // 从60秒开始计时
        // 读取main方法的参数
        if(args.length == 1){
            remaining = Integer.parseInt(args[0]);
        }

        while (true){
            System.out.println("Remaining:" + remaining);
            remaining--;
            if(remaining < 0){
                break;
            }
            try {
                Thread.sleep(1000); // 线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done");
    }
}
