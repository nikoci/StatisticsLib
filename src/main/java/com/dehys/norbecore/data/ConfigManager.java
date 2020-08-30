package com.dehys.norbecore.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class ConfigManager {

    public long SAVEDATAPERIOD;
    public boolean clearCacheOnSave;
    public String PREFIX;
    private final Plugin plugin;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        reloadConfig();
    }

    public void reloadConfig() {
        refreshVariables();
    }

    private void refreshVariables() {
        SAVEDATAPERIOD = plugin.getConfig().getLong("savedata.period") * 1000;
        PREFIX = plugin.getConfig().getString("prefix");
        clearCacheOnSave = plugin.getConfig().getBoolean("savedata.clearcache");
    }

    public Optional<String> getString(String path) {
        return Optional.ofNullable(plugin.getConfig().getString(path));
    }

    public void set(String path, Object value) {
        plugin.getConfig().set(path, value);
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }


}
