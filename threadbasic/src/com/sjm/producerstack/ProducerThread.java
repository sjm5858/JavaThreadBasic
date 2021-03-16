package com.sjm.producerstack;


/**
 * 生产者线程
 * @author sjm5858@126.com
 * @date 2020/12/26 21:03
 */
public class ProducerThread extends Thread {
    // 生产者生产数据就是调用ValueOP类的setValue方法给value字段赋值
    private MyStack stack;

    public ProducerThread(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true){
            stack.push();
        }
    }
}
