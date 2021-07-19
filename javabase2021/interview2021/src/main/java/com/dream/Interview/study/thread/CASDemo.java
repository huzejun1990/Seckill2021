package com.dream.Interview.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : huzejun
 * @Date: 2021/6/30-18:31
 *
 * 1 CAS是什么 ？ ==》compareAndSet
 * 比较并交换
 *
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2021)+"\t current data: " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data: " + atomicInteger.get());
    }
}
