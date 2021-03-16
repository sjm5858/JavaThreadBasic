package com.sjm.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在多线程环境中，把字符串转换为日期对象,多个线程使用同一个SimpleDateFormat对象可能会产生线程安全问题，有异常
 * 为每个线程指定自己的SimpleDateFormat对象,使用ThreadLocal
 *
 * @author sjm5858@126.com
 * @date 2020/12/30 18:59
 */
public class Test02 {

    /**
     * 定义SimpleDateFormat对象，该对象可以把字符串转换为日期
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    /**
     * 定义Runnable接口的实现od
     */
    static class ParesDate implements Runnable {
        private int i = 0;

        public ParesDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                // 构造日期字符串
                String text = "20210101 08:28:" + i%60;
//                Date date = sdf.parse(text);
//                System.out.println(i + " -- " + date);
                // 先判断当前线程是否有SimpleDateFormat对象，如果当前线程没有SimpleDateFormat对象就创建一个，如果有就直接使用。
                if (threadLocal.get() == null){
                    threadLocal.set(new SimpleDateFormat("yyyyMMdd HH:mm:ss") );
                }
                Date date = threadLocal.get().parse(text);
                System.out.println(i + " -- " + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new ParesDate(i)).start();
        }
    }
}
