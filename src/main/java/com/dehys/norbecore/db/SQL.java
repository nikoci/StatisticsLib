package com.dehys.norbecore.db;

import com.dehys.norbecore.main.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {
    public static Connection con;


    public static boolean connect() {
        if (!isConnected()) {
            try {
                final String host = "someHost", database = "someDatabase", username = "someUsername", password = "somePassword";
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password);
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

    public static PreparedStatement prepareStatement(String query) {
        assert con != null;
        try {
            return con.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private static boolean isConnected() {
        return con != null;
    }
}
