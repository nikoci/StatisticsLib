package com.dehys.norbecore.data;

import com.dehys.norbecore.main.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {
    public static Connection connection;


    public static boolean connect() {
        if (!isConnected()) {
            try {
                final String
                        host = Main.getInstance().getConfigManager().getString("sql.host").orElse(null),
                        database = Main.getInstance().getConfigManager().getString("sql.database").orElse(null),
                        username = Main.getInstance().getConfigManager().getString("sql.username").orElse(null),
                        password = Main.getInstance().getConfigManager().getString("sql.password").orElse(null);
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password);
                return true;
            } catch (SQLException var4) {
                Bukkit.getConsoleSender().sendMessage("Connection to SQL-Database unsuccessful. Plugin will be disabled.");
                Bukkit.getServer().getPluginManager().disablePlugin(Main.getInstance());
                return false;
            }
        } else {
            return false;
        }
    }

    public static void setupTables() {
        try {
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS users (uuid VARCHAR(36), playername VARCHAR(16), userid VARCHAR(20))").executeUpdate();
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS plainstatistics (userid VARCHAR(72), statistic VARCHAR(20), amount BIGINT)").executeUpdate();
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS materialstatistics (userid VARCHAR(72), statistic VARCHAR(20), material VARCHAR(30), amount BIGINT)").executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static PreparedStatement prepareStatement(String query) throws SQLException {
        assert connection != null;
        return connection.prepareStatement(query);


    }


    private static boolean isConnected() {
        return connection != null;
    }
}
