package org.dreamndelight.playerstatistics.lib.data;

import org.dreamndelight.playerstatistics.lib.main.PlayerStatistics;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {


    @Override
    public void run() {
        PlayerStatistics.get().getStatisticsManager().saveStatistics(PlayerStatistics.get().getConfigManager().clearCacheOnSave);
    }
}
