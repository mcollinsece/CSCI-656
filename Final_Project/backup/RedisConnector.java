package com.worker.voteapp;

import redis.clients.jedis.Jedis;

public class RedisConnector {

    // Connects to the first Redis instance for votes
    public Jedis connectToRedisVotes() {
        return new Jedis("voteapp-redis", 6379, 0); // Replace with your Redis host and port for votes
    }

    // Connects to the second Redis instance for vote counters
    public Jedis connectToRedisCounters() {
        return new Jedis("voteapp-redis", 6379, 1); // Replace with your Redis host and port for counters
    }
}
