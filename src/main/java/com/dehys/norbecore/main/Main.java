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
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
