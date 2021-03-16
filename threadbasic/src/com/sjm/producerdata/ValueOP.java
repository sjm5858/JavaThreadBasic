package com.sjm.producerdata;

/**
 * @author sjm5858@126.com
 * @date 2020/12/26 20:55
 */
public class ValueOP {
    private String value = "";

    // 定义方法修改value字段的值
    public void setValue() {
        synchronized (this) {
            // 如果value值不是空串，就等待
            while (!value.equalsIgnoreCase("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 设置value字段的值
            String value = System.currentTimeMillis() + " - " + System.nanoTime();
            System.out.println("set设置的值是" + value);
            this.value = value;
            // 在多生产者多消费者环境中，notify不能保证生产者唤醒消费者，如果生产者唤醒的还是生产者，可能会出现假死的情况
//            this.notify();
            this.notifyAll();
        }
    }

    // 定义方法读取value字段的值
    public void getValue(){
        synchronized (this){
            // 如果value是空串就等待
            while (value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 不是空串，读取字段值
            System.out.println("get的值是:" + this.value );
            this.value = "";
            this.notifyAll();
        }
    }
}
