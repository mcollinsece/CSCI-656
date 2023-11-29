package com.voteapi.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void writeToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void publishToChannel(String channel, String message) {
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        try {
            redisConnection.publish(channel.getBytes(), message.getBytes());
        } finally {
            redisConnection.close(); // It's important to close the connection
        }
    }
}
