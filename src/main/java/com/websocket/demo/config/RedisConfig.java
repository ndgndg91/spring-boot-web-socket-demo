package com.websocket.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.websocket.demo.room.ChatRoom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, ChatRoom> redisChatRoomTemplate() {
        final var om = new ObjectMapper();
        // jsr310 - support JSR310
        om.registerModule(new JavaTimeModule());
        // redis serialize
        final var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(ChatRoom.class);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        final var template = new RedisTemplate<String, ChatRoom>();
        template.setConnectionFactory(lettuceConnectionFactory());
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
