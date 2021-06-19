package com.dream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author : huzejun
 * @Date: 2021/6/19-17:14
 */
public class SecKill_redisByScript {

    private static final Logger logger = LoggerFactory.getLogger(SecKill_redisByScript.class);

    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());

        Set<HostAndPort> set = new HashSet<>();

//        doSecKill("201","sk:0101");

    }

    static String secKillScript ="local userid=KEYS[1];\r\n" +
            "local prodid=KEYS[2];\r\n" +
            "local qtkey='sk:'..prodid..\":qt\";\r\n" +
            "local usersKey='sk:'..prodid..\":usr\";\r\n" +
            "local userExists=redis.call(\"sismember\",usersKey,userid);\r\n" +
            "if tonumber(userExists)==1 then \r\n" +
            "   return 2;\r\n" +
            "end\r\n" +
            "local num= redis.call(\"get\" ,qtkey);\r\n" +
            "if tonumber(num)<=0 then \r\n" +
            "   return 0;\r\n" +
            "else \r\n" +
            "   redis.call(\"decr\",qtkey);\r\n" +
            "   redis.call(\"sadd\",usersKey,userid);\r\n" +
            "end\r\n" +
            "return 1" ;

    static String secKillScript2 =
            "local userExists=redis.call(\"sismember\",\"{sk}:0101:usr\",userid);\r\n" +
                    " return 1";

    public static boolean doSecKill(String uid,String prodid){

        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPool.getResource();

        String sha1 = jedis.scriptLoad(secKillScript);
        Object result = jedis.evalsha(sha1, 2, uid, prodid);

        String reString = String.valueOf(result);
        if ("0".equals( reString )){
            System.out.println("已抢空！");
        } else if ("1".equals(reString)){
            System.out.println("抢购成功！！！");
        } else if ("2".equals(reString)){
            System.out.println("该用户已经抢过！！");
        }else {
            System.out.println("抢购异常！！");
        }
        jedis.close();
        return true;

    }
}
