package com.sjm.threadimpl.f1;

/**
 * @author sjm5858
 * @date 2021/4/17 10:58
 */
public class EqualsTest {
    public static void main(String[] args) {
        String a = "Hello World";
        String b = new String("Hello World");
        String c = b; //引用传递
        System.out.println("a == b:" + (a == b));             //false
        System.out.println("b == c:" + (b == c));             //true
        System.out.println("a == c:" + (a == c));             //false
        System.out.println("a.equals(b):" + a.equals(b));   //true
        System.out.println("b.equals(c):" + b.equals(c));   //true
        System.out.println("a.equals(c):" + a.equals(c));   //true
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
