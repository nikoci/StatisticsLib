package com.devflask.statisticslib.plugin;

import com.devflask.statisticslib.lib.commands.ReloadCommand;
import com.devflask.statisticslib.lib.data.*;
import com.devflask.statisticslib.lib.listeners.*;
import com.devflask.statisticslib.lib.main.StatisticsLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Timer;

public class Plugin extends JavaPlugin {

    private StatisticsLib lib;


    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        if (!SQL.connect(this)) return;
        SQL.setupTables();
        lib = new StatisticsLib(this);
        setupListeners();
        Objects.requireNonNull(getCommand("pslreload")).setExecutor(new ReloadCommand(this));
        registerProvider();
        Timer timer = new Timer();
        timer.schedule(new StatisticsTimer(this), getConfigManager().SAVEDATAPERIOD * 1000, getConfigManager().SAVEDATAPERIOD * 1000);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (SQL.connection != null) {
            lib.getUserData().saveData();
            lib.getStatisticsManager().saveStatistics();
        }
    }


    public StatisticsLib getLib() {
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
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new ConsumeItemListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityTameListener(this), this);
    }

    private void registerProvider() {
        Bukkit.getServer().getServicesManager().register(StatisticsLib.class, lib, this, ServicePriority.Highest);
    }
}
