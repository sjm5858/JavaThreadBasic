package com.sjm.question.innerclass;

/**
 * 静态嵌套类(Static Nested Class)和内部类（Inner Class）的不同？
 * Static Nested Class 是被声明为静态（static）的内部类，它可以不依赖于外部类实例被实例化。而通
 * 常的内部类需要在外部类实例化后才能实例化，其语法看起来挺诡异的，如下所示
 *
 * @author sjm5858@126.com
 * @date 2021/4/25 21:07
 */
public class PokerTest {
    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.shuffle(); // 洗牌
        Poker.Card c1 = poker.deal(0); // 发第一张牌
        // 对于非静态内部类 Card
        // 只有通过其外部类 Poker 对象才能创建 Card 对象
        Poker.Card c2 = poker.new Card("红心", 1); // 自己创建一张牌
        System.out.println(c1); // 洗牌后的第一张
        System.out.println(c2); // 打印: 红心 A
    }
}
