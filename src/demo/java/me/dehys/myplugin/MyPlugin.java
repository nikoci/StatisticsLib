package me.dehys.myplugin;

import com.devflask.statisticslib.lib.data.StatisticsManager;
import com.devflask.statisticslib.lib.main.StatisticsLib;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {

    private StatisticsLib lib;

    @Override
    public void onEnable() {
        super.onEnable();
        if (!setupStatisticsLib()) {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    /**
     * This method is used to fetch the active {@link StatisticsLib} instance from Bukkit's {@link RegisteredServiceProvider}
     *
     * @return true if an instance could be found, false if no instance could be found. In this case disable your plugin.
     * You *need* the instance registered to the Provider, creating your own instance can lead to loss of data
     */
    private boolean setupStatisticsLib() {
        if (getServer().getPluginManager().getPlugin("StatisticsLib") == null) {
            return false;
        }
        RegisteredServiceProvider<StatisticsLib> serviceProvider = getServer().getServicesManager().getRegistration(StatisticsLib.class);
        if (serviceProvider == null) {
            return false;
        }
        lib = serviceProvider.getProvider();
        return true;
    }


    /**
     * Use this getter to access the {@link StatisticsLib} instance anywhere in your plugin
     *
     * @return the active instance of {@link StatisticsLib}
     */
    public StatisticsLib getLib() {
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
