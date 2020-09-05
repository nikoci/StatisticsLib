package org.dreamndelight.playerstatistics.data;

import org.dreamndelight.playerstatistics.main.PlayerStatistics;

import java.util.TimerTask;

public class StatisticsTimer extends TimerTask {


    @Override
    public void run() {
        PlayerStatistics.get().getStatisticsManager().saveStatistics(PlayerStatistics.get().getConfigManager().clearCacheOnSave);
    }
}
