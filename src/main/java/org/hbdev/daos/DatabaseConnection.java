package org.hbdev.daos;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.hbdev.constants.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseConnection {
    private static DatabaseConnection instance;

    private static Connection connection;
    private static Server webServer;

    private DatabaseConnection() {
        try {
            // ONLY WITH H2 DATABASE Start H2 web console only once
            if (webServer == null) {
                webServer = Server.createWebServer("-webAllowOthers").start();
               log.info("H2 Web console started at: {}", webServer.getURL());
            }
            // Connect to embedded H2 database
            connection = DriverManager.getConnection(DatabaseProperties.DATABASE_URL, DatabaseProperties.DATABASE_USER, DatabaseProperties.DATABASE_PASSWORD);
            log.info("✅ Connected to H2 database!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("❌ Database connection failed!", e);
            throw new RuntimeException("Failed to connect to H2", e);
        }
    }


    public static DatabaseConnection getInstance()  {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        log.warn("Already connected to database");
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 H2 connection closed.");
                connection = null;
            } catch (SQLException e) {
                System.err.println("❌ Error closing connection.");
                throw new RuntimeException(e);
            }
        }
    }
}
