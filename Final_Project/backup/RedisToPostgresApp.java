package com.worker.voteapp;

import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import redis.clients.jedis.Jedis;

public class RedisToPostgresApp {

    public static void main(String[] args) {
        RedisConnector redisConnector = new RedisConnector();

        try (Jedis jedisVotes = redisConnector.connectToRedisVotes();
             Jedis jedisCounters = redisConnector.connectToRedisCounters();
             Connection postgresConnection = new PostgresConnector().connectToPostgres()) {

            while (true) {
                Set<String> keys = jedisVotes.keys("*");

                for (String key : keys) {
                    String value = jedisVotes.get(key);

                    // Store in PostgreSQL
                    boolean success = storeInPostgres(postgresConnection, key, value);

                    // If store is successful, update Redis counter
                    if (success) {
                        updateRedisCounter(jedisCounters, value);
                    }

                    // Optional: Remove the key from Redis votes after processing
                    jedisVotes.del(key);
                }

                Thread.sleep(10000); // 10 seconds
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean storeInPostgres(Connection connection, String key, String value) throws SQLException {
        String query = "INSERT INTO my_table (user_name, casted_vote) VALUES (?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, key);
            pst.setString(2, value);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void updateRedisCounter(Jedis jedisCounters, String value) {
        String counterKey = (value.equals("candidateA") ? "candidateA:count" : "candidateB:count");
        jedisCounters.incr(counterKey);
    }
}
