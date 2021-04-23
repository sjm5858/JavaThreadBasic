package com.sjm.other.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取 Class 对象有几种方法
 *
 * @author sjm5858
 * @date 2021/4/23 15:21
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
//        Person person = new Person();
        Person person = new Person("sjm","nan",12);
        // 1.调用某个对象的 getClass()方法
        Class<? extends Person> clazz1 = person.getClass();

        // 2.调用某个类的 class 属性来获取该类对应的 Class 对象
        Class<Person> personClass = Person.class;

        // 3.使用 Class 类中的 forName()静态方法(最安全/性能最好)
        Class<?> clazz = Class.forName("com.sjm.other.reflection.Person");

        // 当我们获得了想要操作的类的 Class 对象后，可以通过 Class 类中的方法获取并查看该类中的方法和属性

        //获取 Person 类的所有方法信息
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method) {
            System.out.println(m.toString());
        }

        //获取 Person 类的所有成员属性信息
        Field[] field = clazz.getDeclaredFields();
        for (Field f : field) {
            System.out.println(f.toString());
        }

        //获取 Person 类的所有构造方法信息
        Constructor[] constructor = clazz.getDeclaredConstructors();
        for (Constructor c : constructor) {
            System.out.println(c.toString());
        }

    }
}
