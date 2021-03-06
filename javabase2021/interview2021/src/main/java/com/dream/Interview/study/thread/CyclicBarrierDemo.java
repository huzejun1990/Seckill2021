package com.dream.Interview.study.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author : huzejun
 * @Date: 2021/7/18-16:33
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        // CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("****召唤神龙");
        });

        for (int i = 1; i <= 7 ; i++) {
            final  int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第：" + tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
