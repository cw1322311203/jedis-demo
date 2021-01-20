package com.cw;

import com.cw.util.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author 陈小哥cw
 * @date 2021/1/20 13:42
 */
public class JedisTest {
    public static void main(String[] args) {
        // 1.获取连接对象
        //Jedis jedis = new Jedis("192.168.1.10", 6379);
        Jedis jedis = JedisUtils.getJedis();
        // 2.执行操作
        // jedis.set("name", "cw");
        System.out.println(jedis.dbSize());
        jedis.select(1);
        System.out.println(jedis.keys("*"));

        jedis.lpush("list1", "a", "b", "c", "d");

        List<String> list1 = jedis.lrange("list1", 0, -1);
        for (String s : list1) {
            System.out.println(s);
        }
        // 3.关闭连接
        jedis.close();
    }
}
