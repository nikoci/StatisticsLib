package com.devflask.statisticslib.lib.data;

import com.devflask.statisticslib.plugin.Plugin;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {

    private final Plugin plugin;

    public StatisticsTimer(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getStatisticsManager().saveStatistics(plugin.getConfigManager().clearCacheOnSave);
    }
}
