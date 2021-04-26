package com.sjm.other.base.createobject;

/**
 * 1.使用new关键字
 *
 * @author sjm5858
 * @date 2021/4/25 16:48
 */
public class Test implements Cloneable{

    private String name;

    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test("张三");
    }
}
