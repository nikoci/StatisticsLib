package com.dehys.norbecore.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;


        setupConfig();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void setupConfig() {
        getConfig().addDefault("mysql.host", "localhost");
        getConfig().addDefault("mysql.database", "database");
        getConfig().addDefault("mysql.username", "username");
        getConfig().addDefault("mysql.password", "password");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
