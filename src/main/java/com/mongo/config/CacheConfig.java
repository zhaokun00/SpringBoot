package com.mongo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.bean.CacheStudent;
import com.mongo.bean.CacheTeacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;

//定义一个配置文件
@Configuration
public class CacheConfig {

    //向容器中注入一个UserKeyGenerator类型对象
    @Bean(value = "UserKeyGenerator") //定义了组件的名字
    public KeyGenerator keyGenerator() {
        return new UserKeyGenerator();
    }


//    @Bean
//    public RedisTemplate<Object, CacheTeacher> techerRedisTemplate(
//            RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
//        RedisTemplate<Object, CacheTeacher> template = new RedisTemplate<Object, CacheTeacher>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        Jackson2JsonRedisSerializer<CacheTeacher> serializer = new Jackson2JsonRedisSerializer<CacheTeacher>(CacheTeacher.class);
//        template.setDefaultSerializer(serializer);
//
//        return template;
//    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);

        template.setKeySerializer(new KeyStringRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);

        return cacheManager;
    }
}

//自定义key值的生成策略
@Slf4j
class UserKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... objects) {

        log.info("method:" + method);

        CacheStudent s = (CacheStudent)objects[0]; //将第0个参数进行强制转换

        return s.getId() + s.getName();
    }
}

class KeyStringRedisSerializer implements RedisSerializer {

    private final Charset charset;

    private final String target = "\"";

    private final String replacement = "";

    public KeyStringRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public KeyStringRedisSerializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        String string = object.toString();
        if (string == null) {
            return null;
        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new String(bytes, charset));
    }
}