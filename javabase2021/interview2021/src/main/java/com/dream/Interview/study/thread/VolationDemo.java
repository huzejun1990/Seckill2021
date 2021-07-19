package com.dream.Interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : huzejun
 * @Date: 2021/6/25-19:58
 */

class MyDate {
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    //请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public  void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}
public class VolationDemo {
    public static void main(String[] args) {

        MyDate myDate = new MyDate();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myDate.addPlusPlus();
                    myDate.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        //需要等待上面20个线程全部计算完成后，再用main线程取得最终的结果值看是 多少
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value: " + myDate.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, AtomicInteger number value: " + myDate.atomicInteger);

    }

    private static void seeOKByVolatile() {
        MyDate myDate = new MyDate();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myDate.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myDate.number);
        },"AAA").start();

        //第2个线程就是我们的main线程

        while (myDate.number == 0){
            //main线程就一直在这里等待循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main get number values: " + myDate.number);
    }
}
