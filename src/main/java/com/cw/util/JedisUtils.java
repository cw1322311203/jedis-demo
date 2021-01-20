package com.cw.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @author 陈小哥cw
 * @date 2021/1/20 13:56
 */
public class JedisUtils {

    private static int maxTotal;
    private static int maxIdel;
    private static String host;
    private static int port;
    private static String password;

    private static JedisPoolConfig jpc;

    private static JedisPool jp;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        maxTotal = Integer.parseInt(bundle.getString("redis.maxTotal"));
        maxIdel = Integer.parseInt(bundle.getString("redis.maxIdel"));
        host = bundle.getString("redis.host");
        port = Integer.parseInt(bundle.getString("redis.port"));
        password = bundle.getString("redis.password");
        // Jedis连接池配置
        jpc = new JedisPoolConfig();
        jpc.setMaxTotal(maxTotal);
        jpc.setMaxIdle(maxIdel);
        if (password != null && !"".equals(password)) {
            // redis设置了密码
            jp = new JedisPool(jpc, host, port, 10000, password);
        } else {
            // redis未设置密码
            jp = new JedisPool(jpc, host, port, 10000);
        }
        // 连接池对象
    }

    public static Jedis getJedis() {
        return jp.getResource();
    }


}
