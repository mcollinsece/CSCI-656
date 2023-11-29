package com.worker.voteapp;

import redis.clients.jedis.Jedis;

public class RedisConnector {
    public Jedis connectToRedis() {
        return new Jedis("localhost", 6379); // Replace with your Redis host and port
    }
}

