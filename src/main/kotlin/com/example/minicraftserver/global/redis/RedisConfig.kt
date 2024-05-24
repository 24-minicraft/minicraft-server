package com.example.minicraftserver.global.redis

import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories
class RedisConfig(
    private val redisProperties: RedisProperties,
) {

    @Bean
    public fun redisConnectionFactory(): RedisConnectionFactory {
        val redisConfig: RedisStandaloneConfiguration =
            RedisStandaloneConfiguration(redisProperties.host, redisProperties.port)

        return LettuceConnectionFactory(redisConfig)
    }

    @Bean
    public fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<*, *> {
        return RedisTemplate<ByteArray, ByteArray>().apply{
            setConnectionFactory(connectionFactory)
        }
    }
}