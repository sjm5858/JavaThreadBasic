package com.sjm.other.base.createobject;

/**
 * 5.Object对象的clone()方法
 *
 * @author sjm5858
 * @date 2021/4/25 16:59
 */
public class Test05 {
    public static void main(String[] args) throws Exception {
        Test t1 = new Test("张三");
        Test t2 = (Test) t1.clone();
        System.out.println(t1 == t2);
        System.out.println(t2.getName());
    }
}
