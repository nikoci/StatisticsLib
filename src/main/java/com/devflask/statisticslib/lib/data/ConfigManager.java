package com.devflask.statisticslib.lib.data;

import com.devflask.statisticslib.lib.enums.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;


public class ConfigManager {

    private final Plugin plugin;
    private final HashMap<Statistic, Boolean> enabledStatistics = new HashMap<>();
    public long SAVEDATAPERIOD;
    public boolean clearCacheOnSave;
    public String PREFIX;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        reloadConfig();
    }

    public void reloadConfig() {
        plugin.getLogger().log(Level.INFO, "========== Reloading config ==========");
        refreshVariables();
        refreshEnabledStatistics();
        plugin.getLogger().log(Level.INFO, "========== Config reloaded ==========");
    }

    private void refreshEnabledStatistics() {
        Objects.requireNonNull(getConfig().getConfigurationSection("statistics.enabled")).getKeys(false).forEach(key -> {
            enabledStatistics.put(Statistic.getByKey(key), getConfig().getBoolean("statistics.enabled." + key));
            plugin.getLogger().log(Level.INFO, "Statistic \"" + key + "\" is " + (enabledStatistics.get(Statistic.getByKey(key)) ? "ENABLED" : "DISABLED"));
        });
    }

    private void refreshVariables() {
        SAVEDATAPERIOD = plugin.getConfig().getLong("savedata.period");
        PREFIX = plugin.getConfig().getString("prefix");
        clearCacheOnSave = plugin.getConfig().getBoolean("savedata.clearcache");
    }

    public boolean isStatisticDisabled(Statistic statistic) {
        return enabledStatistics.getOrDefault(statistic, false);
    }

    public HashMap<Statistic, Boolean> getEnabledStatistics() {
        return enabledStatistics;
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }


    public void setStatisticEnabled(Statistic statistic, boolean bool) {
        enabledStatistics.put(statistic, bool);
    }
}
