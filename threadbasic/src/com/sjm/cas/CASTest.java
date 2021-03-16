package com.sjm.cas;

/**
 * 使用CAS实现一个线程安全的计数器
 *
 * @author sjm5858@126.com
 * @date 2020/12/25 18:32
 */
public class CASTest {
    public static void main(String[] args) {

        CASCounter casCounter = new CASCounter();

        for (int i = 0; i < 100000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(casCounter.incrementAndGet());
                }
            }).start();
        }
    }
}

class CASCounter {
    // 使用volatile修饰value值使线程可见
    volatile private long value;

    public long getValue() {
        return value;
    }

    // 定义Compare And Swap 方法
    private boolean compareAndSwap(long expectedValue, long newValue) {
        // 如果当前的value值与期望的值一样，就把当前的Value字段替换为newValue
        synchronized (this) {
            if (value == expectedValue) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }

    // 定义自增的方法
    public long incrementAndGet() {
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue + 1;
        } while (!compareAndSwap(oldValue, newValue));
        return newValue;
    }
}