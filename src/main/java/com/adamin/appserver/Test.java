/**
 * Copyright (C), 2020-2021
 * FileName: Test
 * Date:     2021/3/17 10:32
 * Description:
 */
package com.adamin.appserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/17 10:32
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
    public synchronized static void get(Thread thread){
        System.out.println("thread.getName() = " + thread.getName()+"starttime "+System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                get(Thread.currentThread());
            }
        }).start();
    }
}
