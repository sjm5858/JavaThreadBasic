package com.sjm.atomics.atomicintegerfiled;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author sjm5858@126.com
 * @date 2020/12/25 20:15
 */
public class SubThread extends Thread {
    private User user; // 要更新的User对象
    // 创建AtomicIntegerFieldUpdater更新器
    private AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public SubThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        // 在子线程中对user对象的age字段自增10次
        for (int i = 0; i < 10; i++) {
            System.out.println(updater.getAndIncrement(user));
        }
    }
}
