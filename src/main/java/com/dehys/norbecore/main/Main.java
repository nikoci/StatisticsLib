package com.dehys.norbecore.main;

import com.dehys.norbecore.data.*;
import com.dehys.norbecore.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public class Main extends JavaPlugin {

    private static Main instance;
    private UserData userData;
    private ConfigManager configManager;
    private StatisticsManager statisticsManager;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        saveDefaultConfig();
        configManager = new ConfigManager(this);
        if (!SQL.connect()) return;
        SQL.setupTables();
        userData = UserData.retrieveData();
        statisticsManager = new StatisticsManager();
        setupListeners();

        Timer timer = new Timer();
        timer.schedule(new StatisticsTimer(), getConfigManager().SAVEDATAPERIOD * 1000, getConfigManager().SAVEDATAPERIOD * 1000);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        userData.saveData();
        statisticsManager.saveStatistics();
    }


    public UserData getUserData() {
        return userData;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new ItemBreakListener(), this);
        getServer().getPluginManager().registerEvents(new ItemCraftListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);
        getServer().getPluginManager().registerEvents(new ProjectileHitListener(), this);
        getServer().getPluginManager().registerEvents(new FishListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }
}
