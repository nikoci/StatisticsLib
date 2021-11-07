package com.devflask.statisticslib.lib.data;

import com.devflask.statisticslib.plugin.StatisticsPlugin;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {

    private final StatisticsPlugin statisticsPlugin;

    public StatisticsTimer(StatisticsPlugin statisticsPlugin) {
        this.statisticsPlugin = statisticsPlugin;
    }

    @Override
    public void run() {
        statisticsPlugin.getStatisticsManager().saveStatistics(statisticsPlugin.getConfigManager().clearCacheOnSave);
    }
}
