package com.sjm.other.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 利用反射动态创建对象实例
 *
 * @author sjm5858
 * @date 2021/4/23 15:32
 */
public class Test02 {
    public static void main(String[] args)  {
        // Class 对象的 newInstance()
        // 1. 使用 Class 对象的 newInstance()方法来创建该 Class 对象对应类的实例，但是这种方法要求
        // 该 Class 对象对应的类有默认的空构造器。

        Class clazz = null;
        try {
            clazz = Class.forName("com.sjm.other.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //使用.newInstane 方法创建对象
        Person person = null;
        try {
            person = (Person) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(person);

        //获取构造方法并创建对象
        Constructor constructor = null;
        try {
            constructor = clazz.getDeclaredConstructor(String.class, String.class, Integer.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //创建对象并设置属性13/04/2018
        try {
            Person p2 = (Person) constructor.newInstance("sjm", "nan", 20);
            System.out.println(p2);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
