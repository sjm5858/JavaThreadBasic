package com.sjm.wait;

/**
 * 演示wait()/notify()方法需要放在同步代码块中，否则会产生java.lang.IllegalMonitorStateException异常
 * 任何对象都可以调用wait()/notify()，这两个方法是从Object类继承来的
 *
 * @author sjm5858@126.com
 * @date 2020/12/25 21:43
 */
public class Test01 {
    public static void main(String[] args) {
        try {
            String test = "wkcto";
            test.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
