package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnectionTest {

    // Database connection details
    private String urlDB = "jdbc:postgresql://localhost:5432/WorkPal";
    private String usernameDB = "postgres";
    private String passwordDB = "123";

    public  void main() {
        PostgreSQLConnectionTest testConnection = new PostgreSQLConnectionTest();
        testConnection.connectToDatabase();
    }

    public void connectToDatabase() {
        Connection connection = null;
        try {
            // Establishing a connection
            connection = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            if (connection != null) {
                System.out.println("Connection OK");
            }
        } catch (SQLException e) {
            // Print error message if connection fails
            System.out.println("Connection Failed");
            e.printStackTrace();
        } finally {
            // Close the connection if it's open
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
