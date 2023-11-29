package com.worker.voteapp;

import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import redis.clients.jedis.Jedis;

public class RedisToPostgresApp {

    public static void main(String[] args) {
        RedisConnector redisConnector = new RedisConnector();
        PostgresConnector postgresConnector = new PostgresConnector();

        try (Jedis jedis = redisConnector.connectToRedis();
             Connection postgresConnection = postgresConnector.connectToPostgres()) {

            while (true) {
                // Example: Fetch all keys. Modify as per your requirement.
                Set<String> keys = jedis.keys("*");

                for (String key : keys) {
                    String value = jedis.get(key);

                    // Store in PostgreSQL
                    storeInPostgres(postgresConnection, key, value);

                    // Optional: Remove the key from Redis after processing
                    jedis.del(key);
                }

                // Sleep for a while before polling again
                Thread.sleep(10000); // 10 seconds
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void storeInPostgres(Connection connection, String key, String value) throws SQLException {
        String query = "INSERT INTO my_table (user_name, casted_vote) VALUES (?, ?)"; // Replace with your table and columns
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, key);
            pst.setString(2, value);
            pst.executeUpdate();
        }
    }
}