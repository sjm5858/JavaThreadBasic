package com.sjm.producerdata;

import java.util.Map;

/**
 * 测试一生产，一消费的情况
 * @author sjm5858@126.com
 * @date 2020/12/26 20:54
 */
public class Test {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();

        ProducerThread p = new ProducerThread(valueOP);
        ConsumerThread c = new ConsumerThread(valueOP);

        p.start();
        c.start();
        // 生产和消费交替运行
    }
}
