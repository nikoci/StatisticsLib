package com.dehys.norbecore.main;

import com.dehys.norbecore.data.ConfigManager;
import com.dehys.norbecore.data.SQL;
import com.dehys.norbecore.data.UserData;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private UserData userData;
    private ConfigManager configManager;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        if (!SQL.connect()) return;

        userData = UserData.retrieveData();
        configManager = new ConfigManager(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        userData.saveData();
    }


    public UserData getUserData() {
        return userData;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
