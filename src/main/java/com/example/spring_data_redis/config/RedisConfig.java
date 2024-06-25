package com.example.spring_data_redis.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.security.PublicKey;


@Configuration
@EnableRedisRepositories
public class RedisConfig {

    //create the redis connection factory
    @Bean
    public JedisConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration Configuration = new RedisStandaloneConfiguration();
        //now set the host and port
        Configuration.setHostName("localhost");
        Configuration.setPort(6379);
        return new JedisConnectionFactory(Configuration);
    }

    //To access redis server from our application we need templete , now create the bean of redis templete
    @Bean
    @Primary
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}
