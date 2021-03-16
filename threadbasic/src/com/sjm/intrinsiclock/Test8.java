package com.sjm.intrinsiclock;

/**
 * 脏读
 *   出现读取属性值出现了一些意外，读取的是中间值，而不是修改之后的值
 *   出现脏读的原因是 对共享数据的修改与对共享数据的读取不同步
 *   解决方法：
 *       不仅对修改数据的代码块进行同步，还要对读取数据的代码块进行同步
 * @author sjm5858@126.com
 * @date 2020/12/23 20:58
 */
public class Test8 {
    public static void main(String[] args) throws InterruptedException {
        PublicValue publicValue = new PublicValue();
        SubThread t1 = new SubThread(publicValue);
        t1.start();

        // 为了确定设置成功
        Thread.sleep(100);
        // 在main线程中读取用户名，密码
        publicValue.getValue();

    }

    // 定义线程，设置用户名和密码
    static class SubThread extends Thread{
        private PublicValue publicValue;
        public SubThread(PublicValue publicValue){
            this.publicValue = publicValue;
        }

        @Override
        public void run() {
            publicValue.setValue("asdfjaslkf","123");
        }
    }

    static class PublicValue{
        private String name ="sjmcto";
        private String pwd ="666";

        public synchronized void getValue(){
            System.out.println(Thread.currentThread().getName() + ", getter -- name: " + name +
                    ", pwd: " + pwd);
        }
        public synchronized void  setValue(String name ,String pwd){
            this.name = name;
            try {
                Thread.sleep(1000); // 模拟操作那么属性需要一定的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.pwd = pwd;
            System.out.println(Thread.currentThread().getName() + ", setter -- name: " + name +
                    ", pwd: " + pwd);
        }
    }
}
