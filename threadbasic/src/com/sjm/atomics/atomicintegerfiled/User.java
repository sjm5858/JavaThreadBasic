package com.sjm.atomics.atomicintegerfiled;

/**
 * AtomicIntegerFieldUpdater 更新的字段必须使用volatile修饰
 * @author sjm5858@126.com
 * @date 2020/12/25 20:13
 */
public class User {
    int id;
    volatile int age;

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
