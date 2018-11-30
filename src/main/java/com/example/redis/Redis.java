package com.example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.List;

public class Redis {


    public static void main(String[] args) {
        //连接本地的 Redis 服务，不设密码的情况
      //  Jedis jedis = new Jedis("localhost");
        //连接Jedis jedis有密码的情况 连接方式
        //JedisShardInfo shardInfo = new JedisShardInfo("127.0.0.1", "3306");
        JedisShardInfo shardInfo = new JedisShardInfo("192.168.11.66", "6379");
        shardInfo.setPassword("123456");
        Jedis jedis = new Jedis(shardInfo);
        jedis.connect();

        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

        String item = jedis.lindex("site-list",1);
        System.out.println(item);

        Long item1 = jedis.publish("sohu","helloworld");
        System.out.println("send:"+item1);

        String item2 = jedis.substr("sohu",0,-1);
        System.out.println("send:"+item2);
    }


//    public Long push(String key ,String param){
//        return jedis.lpush("site-list", param);
//    }
}
