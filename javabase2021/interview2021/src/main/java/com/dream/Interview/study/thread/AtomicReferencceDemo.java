package com.dream.Interview.study.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author : huzejun
 * @Date: 2021/7/6-19:41
 */

@Getter
@ToString
@AllArgsConstructor
class User
{
    String userName;
    int age;
}

public class AtomicReferencceDemo {

    public static void main(String[] args) {


        User z3 = new User("z3",22);
        User lisi = new User("lisi",25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,lisi) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,lisi) + "\t" + atomicReference.get().toString());
    }



}
