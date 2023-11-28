package com.worker.voteapp;
import redis.clients.jedis.Jedis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RedisToPostgresWorker 
{
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private static final String POSTGRES_URL = "jdbc:postgresql://localhost/mydb";
    private static final String POSTGRES_USER = "postgres";
    private static final String POSTGRES_PASSWORD = "password";

    public static void main(String[] args) {
        RedisToPostgresWorker worker = new RedisToPostgresWorker();
        String redisData = worker.readFromRedis("myKey"); // Replace 'myKey' with your Redis key
        worker.writeToPostgres(redisData);
    }

    private String readFromRedis(String key) {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeToPostgres(String data) {
        String sql = "INSERT INTO my_table (column_name) VALUES (?)"; // Replace with your SQL

        try (Connection conn = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, data);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}