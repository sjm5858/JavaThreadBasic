package com.sjm.other.base.createobject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 3.构造函数对象的newInstance()方法
 *
 * @author sjm5858
 * @date 2021/4/25 16:51
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        Constructor constructor;
        try {
            constructor = Test.class.getConstructor();
            Test t = (Test) constructor.newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException |
                NoSuchMethodException |
                SecurityException e) {
            e.printStackTrace();
        }
    }
}
