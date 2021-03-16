package com.sjm.producerstack;


/**
 * 消费者线程
 * @author sjm5858@126.com
 * @date 2020/12/26 21:03
 */
public class ConsumerThread extends Thread {
    private MyStack stack;

    public ConsumerThread(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true){
            stack.pop();
        }
    }
}
