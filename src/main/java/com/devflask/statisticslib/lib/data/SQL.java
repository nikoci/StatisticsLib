package com.devflask.statisticslib.lib.data;

import com.devflask.statisticslib.plugin.Plugin;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {
    public static Connection connection;


    public static boolean connect(Plugin plugin) {
        if (!isConnected()) {
            try {
                final String
                        host = plugin.getConfig().getString("sql.host"),
                        database = plugin.getConfig().getString("sql.database"),
                        username = plugin.getConfig().getString("sql.username"),
                        password = plugin.getConfig().getString("sql.password");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password);
                return true;
            } catch (SQLException var4) {
                Bukkit.getConsoleSender().sendMessage("Connection to SQL-Database unsuccessful. Plugin will be disabled.");
                Bukkit.getServer().getPluginManager().disablePlugin(plugin);
                return false;
            }
        } else {
            return false;
        }
    }

    public static void setupTables() {
        try {
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS userdata (uuid VARCHAR(36) UNIQUE, username VARCHAR(16), userid VARCHAR(20))").executeUpdate();
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS plainstatistics (userid VARCHAR(72), statistic VARCHAR(20), amount BIGINT)").executeUpdate();
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS materialstatistics (userid VARCHAR(72), statistic VARCHAR(20), material VARCHAR(30), amount BIGINT)").executeUpdate();
            SQL.prepareStatement("CREATE TABLE IF NOT EXISTS entitystatistics (userid VARCHAR(72), statistic VARCHAR(20), entity VARCHAR(30), amount BIGINT)").executeUpdate();

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
