package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/zoo?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";  // Замініть на вашого користувача
    private static final String PASSWORD = "yourpassword";  // Замініть на ваш пароль

    // Підключення до бази даних
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Створення бази даних, якщо вона не існує
    public static void createDatabaseIfNotExists() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC", USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS zoo";
            stmt.executeUpdate(createDatabaseQuery);
            System.out.println("Database 'zoo' is ready!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Створення таблиці, якщо вона не існує
    public static void createTableIfNotExists() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            String createTableQuery = "CREATE TABLE IF NOT EXISTS animals (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "species VARCHAR(100) NOT NULL, " +
                    "age INT NOT NULL)";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table 'animals' is ready!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
