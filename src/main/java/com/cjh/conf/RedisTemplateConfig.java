package com.cjh.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisTemplateConfig
 * @Description: redis配置
 * @Author: chenjunhan
 * @CreateDate: 2019/11/12 9:19
 */

@Component
public class RedisTemplateConfig {
    /** redis数据库*/
    @Value("${spring.redis.database}")
    private int redisDatabase;
    /** redis数据库ip*/
    @Value("${spring.redis.host}")
    private String redisHost;
    /** redis数据库端口*/
    @Value("${spring.redis.port}")
    private int redisPort;
    /** redis数据库端口*/
    @Value("${spring.redis.password}")
    private String password;


    @Bean(name = "redisTemplate0")
    public StringRedisTemplate redisTemplate() {
        return buildRedisTemplate(buildConnectionFactory(jedisPoolConfig(),redisDatabase));
    }


    protected StringRedisTemplate buildRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(stringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * 连接池配置信息
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(100);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(20);
        //当池内没有可用的连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(10000);
        return jedisPoolConfig;
    }

    /**
     * jedis连接工厂
     * @param jedisPoolConfig
     * @param database
     * @return
     */
    public JedisConnectionFactory buildConnectionFactory(JedisPoolConfig jedisPoolConfig, int database) {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        //设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(redisHost);
        //设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(database);
        System.out.println(password);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        //设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(redisPort);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisBuilder=(JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        jedisBuilder.poolConfig(jedisPoolConfig);
        JedisClientConfiguration jedisClientConfiguration = jedisBuilder.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

}
