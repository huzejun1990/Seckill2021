package com.dream.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @Author : huzejun
 * @Date: 2021/6/21-0:38
 *
 * 演示redis集群操作
 */
public class RedisClusterDemo {

    public static void main(String[] args) {

        //创建对象
        HostAndPort hostAndPort = new HostAndPort("192.168.31.60", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        //进行操作
        jedisCluster.set("b1","value1");

        String value = jedisCluster.get("b1");
        System.out.printf("value: " + value);
        jedisCluster.close();

    }
}
