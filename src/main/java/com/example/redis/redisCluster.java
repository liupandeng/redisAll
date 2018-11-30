package com.example.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;

public class redisCluster {
    public static void main(String[] args) throws IOException {

        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.11.66",8000));
        nodes.add(new HostAndPort("192.168.11.66",8001));
        nodes.add(new HostAndPort("192.168.11.66",8002));
        nodes.add(new HostAndPort("192.168.11.66",8003));
        nodes.add(new HostAndPort("192.168.11.66",8004));
        nodes.add(new HostAndPort("192.168.11.66",8005));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key","hello_world");
        String str = cluster.get("key");
        System.out.println("----"+str);
        cluster.close();

    }

}
