package org.dreamndelight.playerstatistics.lib.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.dreamndelight.playerstatistics.lib.data.ConfigManager;
import org.dreamndelight.playerstatistics.lib.data.StatisticsManager;
import org.dreamndelight.playerstatistics.lib.data.StatisticsTimer;
import org.dreamndelight.playerstatistics.lib.data.UserData;
import org.dreamndelight.playerstatistics.lib.listeners.*;

import java.util.Timer;

public class PlayerStatistics extends JavaPlugin {

    private PlayerStatisticsLib lib;


    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        lib = new PlayerStatisticsLib(this);
        setupListeners();
        registerProvider();
        Timer timer = new Timer();
        timer.schedule(new StatisticsTimer(this), getConfigManager().SAVEDATAPERIOD * 1000, getConfigManager().SAVEDATAPERIOD * 1000);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        lib.getUserData().saveData();
        lib.getStatisticsManager().saveStatistics();
    }


    public PlayerStatisticsLib getLib() {
        return lib;
    }

    public UserData getUserData() {
        return lib.getUserData();
    }

    public ConfigManager getConfigManager() {
        return lib.getConfigManager();
    }

    public StatisticsManager getStatisticsManager() {
        return lib.getStatisticsManager();
    }

    private void setupListeners() {
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new DropListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemCraftListener(this), this);
        getServer().getPluginManager().registerEvents(new EnchantListener(this), this);
        getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);
        getServer().getPluginManager().registerEvents(new FishListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new RaidListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityBreedListener(this), this);
    }

    private void registerProvider() {
        Bukkit.getServer().getServicesManager().register(PlayerStatisticsLib.class, lib, this, ServicePriority.Highest);
    }
}
