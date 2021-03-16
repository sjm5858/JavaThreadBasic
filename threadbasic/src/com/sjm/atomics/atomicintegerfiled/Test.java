package com.sjm.atomics.atomicintegerfiled;

/**
 * @author sjm5858@126.com
 * @date 2020/12/25 20:13
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        User user = new User(1234, 10);
        // 开户10个线程
        for (int i = 0; i < 10; i++) {
            new SubThread(user).start();
        }
        Thread.sleep(1000);
        System.out.println(user);
    }
}
