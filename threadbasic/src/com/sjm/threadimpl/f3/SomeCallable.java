package com.sjm.threadimpl.f3;

import com.sun.imageio.spi.OutputStreamImageOutputStreamSpi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author sjm5858@126.com
 * @date 2021/4/16 23:21
 */
public class SomeCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        return "sjm666";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> oneCallable = new SomeCallable();
        //由Callable<Integer>创建一个FutureTask<Integer>对象：
        FutureTask<String> oneTask = new FutureTask<>(oneCallable);
        //注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
        //由FutureTask<Integer>创建一个Thread对象：
        Thread oneThread = new Thread(oneTask);
        oneThread.start();
        //至此，一个线程就创建完成了。
        String s = oneTask.get();
        System.out.println(s);
    }
}
