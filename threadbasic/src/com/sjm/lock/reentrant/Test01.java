package com.sjm.lock.reentrant;

/**
 * 演示锁的可重入性
 * @author sjm5858@126.com
 * @date 2020/12/30 19:40
 */
public class Test01 {
    public synchronized void sm1(){
        System.out.println("方法1");
        // 线程执行sm1方法，默认this作为锁对象，在sm1方法中调用了sm2方法，注意当前线程还是持有this锁对象的
        // sm2同步方法默认的锁对象也是this对象，要执行sm2必须先获得this锁对象，当前this对象被当前线程持有，
        // 可以再次获得this对象，这就是锁的可重入性，假设锁不可重入的话，可能会造成死锁
        sm2();
    }

    private synchronized void sm2() {
        System.out.println("方法2");
        sm3();
    }

    private synchronized void sm3() {
        System.out.println("方法3");
    }

    public static void main(String[] args) {

        Test01 obj = new Test01();
        new Thread(new Runnable() {
            @Override
            public void run() {
               obj.sm1();
            }
        }).start();
    }
}
