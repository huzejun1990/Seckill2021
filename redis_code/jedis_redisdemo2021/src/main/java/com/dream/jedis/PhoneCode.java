package com.dream.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @Author : huzejun
 * @Date: 2021/6/19-0:48
 */
public class PhoneCode {
    public static void main(String[] args) {
        //模拟验证码发送
        verifyCode("13800138000");

   //    getRedisCode("13800138000","312125"); //438247
            // 332086  801875 681327 438685 455296
//        getRedisCode("13800138000","801875");

/*        final String code = getCode();
        System.out.println(code);*/

    }

    //3 验证码校验
    public static void getRedisCode(String phone,String code){
        //从redis中获取
        Jedis jedis = new Jedis("192.168.1.62",6379);
        //验证码key
        String codekey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codekey);
        //判断
        if (redisCode.equals(code)){
            System.out.println("成功!");
        } else {
            System.out.println("失败！");
        }
        jedis.close();


    }

    //2 每个手机每天只能发送三次，验证码放到 redis中，设置过期时间120秒
    public static void verifyCode(String phone){
        Jedis jedis = new Jedis("192.168.1.62",6379);

        //拼接 key
        //手机发送次数 key
        String countKey = "VerifyCode" + phone + ":count";

        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";

        //每个手机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            //没有发送次数，第一次发送
            //设置发送次数是1
            jedis.setex(countKey,24*60*60, "1");
        } else if (Integer.parseInt(count) <= 2){
            //发送次数+1
            jedis.incr(countKey);
        } else if (Integer.parseInt(count)>2){
            //发送三次，不能再发送
            System.out.println("今天的发送次数已经超三次了");
            jedis.close();
            return;  // 加return 超3次，不再生成新的验证码
        }

        //发送的验证码，放到redis中
        String vcode = getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();
    }

    //1 生成6位数据验证码
    public static String getCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
