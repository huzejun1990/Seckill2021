package com.dream.Interview.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author : huzejun
 * @Date: 2021/7/18-23:01
 * 阻塞队列SynchronousQueue演示
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t  put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t  put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t  put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }

}
