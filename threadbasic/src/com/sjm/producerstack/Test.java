package com.sjm.producerstack;

/**
 * 测试一生产，一消费的情况
 * @author sjm5858@126.com
 * @date 2020/12/26 21:28
 */
public class Test {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        ProducerThread p = new ProducerThread(stack);
        ConsumerThread c = new ConsumerThread(stack);

        p.start();
        c.start();
        // 生产和消费交替运行
    }
}
