package com.sjm.producerdata;

/**
 * 定义线程类模拟消费者
 * @author sjm5858@126.com
 * @date 2020/12/26 21:03
 */
public class ConsumerThread extends Thread {
    // 消费者消费数据就是调用ValueOP类的getValue方法
    private ValueOP obj;

    public ConsumerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.getValue();
        }
    }
}
