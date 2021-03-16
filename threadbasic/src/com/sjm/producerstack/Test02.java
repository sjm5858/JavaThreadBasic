package com.sjm.producerstack;

/**
 * 测试一生产，多消费的情况
 * @author sjm5858@126.com
 * @date 2020/12/26 21:28
 */
public class Test02 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        ProducerThread p1 = new ProducerThread(stack);
        ProducerThread p2 = new ProducerThread(stack);
        ProducerThread p3 = new ProducerThread(stack);
        ConsumerThread c1 = new ConsumerThread(stack);
        ConsumerThread c2 = new ConsumerThread(stack);
        ConsumerThread c3 = new ConsumerThread(stack);
        p1.setName("生产者1号");
        p2.setName("生产者2号");
        p3.setName("生产者3号");
        c1.setName("消费者1号");
        c2.setName("消费者2号");
        c3.setName("消费者3号");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
