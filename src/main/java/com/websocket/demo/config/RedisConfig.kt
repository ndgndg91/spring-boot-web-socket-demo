package com.websocket.demo.config

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import com.websocket.demo.room.ChatRoom
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

@Configuration
open class RedisConfig {
    @Bean
    open fun lettuceConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    open fun redisTemplate(): RedisTemplate<String, ChatRoom> {
        val om = ObjectMapper()
        // jsr310 - support JSR310
        om.registerModule(JavaTimeModule())
        // redis serialize
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(
            ChatRoom::class.java
        )
        jackson2JsonRedisSerializer.setObjectMapper(om)
        val template = RedisTemplate<String, ChatRoom>()
        template.connectionFactory = lettuceConnectionFactory()
        template.keySerializer = jackson2JsonRedisSerializer
        template.valueSerializer = jackson2JsonRedisSerializer
        template.hashKeySerializer = jackson2JsonRedisSerializer
        template.hashValueSerializer = jackson2JsonRedisSerializer
        template.afterPropertiesSet()
        return template
    }
}