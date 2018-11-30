package com.example.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class RedisSentinel {
    private static Logger logger = (Logger) LoggerFactory.getLogger(RedisSentinel.class);
    public static void main(String[] args) {
        String masterName="mymaster";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.11.66:26379");
        sentinels.add("192.168.11.66:26380");
        sentinels.add("192.168.11.66:26381");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName,  sentinels);
        int counter=0;
        while(true){
            counter ++ ;
            Jedis jedis = null;
            try {
                jedis = jedisSentinelPool.getResource();
                int index = new Random().nextInt(100000);
                String key = "k-"+index;
                String value = "v-"+index;
                jedis.set(key,value);
                if(counter % 100==0){
                    logger.info("{} value is {}",key,jedis.get(key));
                }
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }finally{
                if(jedis !=null){
                    jedis.close();
                }
            }

        }



    }
}
