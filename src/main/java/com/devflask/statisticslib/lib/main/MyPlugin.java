package com.devflask.statisticslib.lib.main;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import com.devflask.statisticslib.lib.data.StatisticsManager;

public class MyPlugin extends JavaPlugin {

    private PlayerStatisticsLib lib;

    @Override
    public void onEnable() {
        super.onEnable();
        if (!setupPlayerStatisticsLib()) {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    /**
     * This method is used to fetch the active {@link PlayerStatisticsLib} instance from Bukkit's {@link RegisteredServiceProvider}
     *
     * @return true if an instance could be found, false if no instance could be found. In this case disable your plugin.
     * You *need* the instance registered to the Provider, creating your own instance can lead to loss of data
     */
    private boolean setupPlayerStatisticsLib() {
        if (getServer().getPluginManager().getPlugin("PlayerStatisticsLib") == null) {
            return false;
        }
        RegisteredServiceProvider<PlayerStatisticsLib> serviceProvider = getServer().getServicesManager().getRegistration(PlayerStatisticsLib.class);
        if (serviceProvider == null) {
            return false;
        }
        lib = serviceProvider.getProvider();
        return true;
    }


    /**
     * Use this getter to access the {@link PlayerStatisticsLib} instance anywhere in your plugin
     *
     * @return the active instance of {@link PlayerStatisticsLib}
     */
    public PlayerStatisticsLib getLib() {
        return lib;
    }


    /**
     * Use this getter to access the {@link StatisticsManager} instance.
     * The {@link StatisticsManager} provides all methods for handling statistics, most importantly, for retrieving them
     *
     * @return the active instance of {@link StatisticsManager}
     */
    public StatisticsManager getStatisticsManager() {
        return lib.getStatisticsManager();
    }
}
