package com.sjm.question;

/**
 * @author sjm5858@126.com
 * @date 2021/4/25 20:46
 */
public class Out {
    private static int a;
    private int b;

    public class Inner {
        public void print() {
            System.out.println(a);
            System.out.println(b);
        }
    }

    public static void main(String[] args) {
        Out out = new Out();
        Inner inner = out.new Inner();
    }
}
