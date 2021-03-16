package com.sjm.producerdata;

/**
 * 测试多生产，多消费的情况
 * @author sjm5858@126.com
 * @date 2020/12/26 20:54
 */
public class Test2 {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();

        ProducerThread p1 = new ProducerThread(valueOP);
        ProducerThread p2 = new ProducerThread(valueOP);
        ProducerThread p3 = new ProducerThread(valueOP);
        ConsumerThread c1 = new ConsumerThread(valueOP);
        ConsumerThread c2 = new ConsumerThread(valueOP);
        ConsumerThread c3 = new ConsumerThread(valueOP);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
        // 生产和消费交替运行
    }
}
