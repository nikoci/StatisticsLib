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
                        host = Main.getInstance().getConfigManager().getString("sql.host"),
                        database = Main.getInstance().getConfigManager().getString("sql.database"),
                        username = Main.getInstance().getConfigManager().getString("sql.username"),
                        password = Main.getInstance().getConfigManager().getString("sql.password");
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

    public static PreparedStatement prepareStatement(String query) throws SQLException {
        assert connection != null;
        return connection.prepareStatement(query);


    }

    public static Connection getConnection() {
        return connection;
    }

    private static boolean isConnected() {
        return connection != null;
    }
}
