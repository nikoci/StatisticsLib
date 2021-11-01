package org.dreamndelight.playerstatistics.lib.data;

import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {

    private final PlayerStatistics plugin;

    public StatisticsTimer(PlayerStatistics plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getLib().getStatisticsManager().saveStatistics(plugin.getLib().getConfigManager().clearCacheOnSave);
    }
}
