package com.sjm.other.base.createobject;

/**
 * 2.Class对象的newInstance()方法
 *
 * @author sjm5858
 * @date 2021/4/25 16:49
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        String className = "org.b3log.solo.util.Test";
        Class clazz = Class.forName(className);
        Test t = (Test) clazz.newInstance();
    }
}
