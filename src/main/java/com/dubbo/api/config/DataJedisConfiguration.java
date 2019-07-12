package com.dubbo.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-12:15:15
 * Modify date: 2019-07-12:15:15
 */
@Slf4j
@SpringBootConfiguration
public class DataJedisConfiguration {
    @Value("${redis.host}")
    private  String host;
    @Value("${redis.port}")
    private  int port;
    @Value("${redis.timeout}")
    private  int timeout;
    @Value("${redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        log.info("Create JedisConnectionFactory successful");
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setTimeout(timeout);
        return factory;
    }
    @Bean
    public JedisPool redisPoolFactory() {
        log.info("JedisPool init successful，host -> [{}]；port -> [{}]", host, port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }
}
