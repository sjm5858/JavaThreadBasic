package com.sjm.threadmethod.p3sleep;

/**
 * @author sjm5858@126.com
 * @date 2020/12/21 21:11
 */
public class SubThread4 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("run, threadName = " + Thread.currentThread().getName() +
                    ", begin= " + System.currentTimeMillis());

            Thread.sleep(2000);// 当前线程睡眠2000毫秒

            System.out.println("run, threadName = " + Thread.currentThread().getName() +
                    ", end= " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            // 在子线程的run方法中，如果有受检异常（编译时异常）需要处理，只有选择捕获处理，不能抛出处理
            e.printStackTrace();
        }
    }
}
